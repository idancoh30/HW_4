import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A MyReentrantLock is a mutual exclusion mechanism that allows threads to reenter into a lock on a resource (mulitple times)
 */
public class MyReentrantLock implements Lock {
    private Thread lockingThread; // current locking thread.
    private int lockCounter; // counts how many times a thread acquired the lock.
    private AtomicBoolean isLocked; // indicated whether thread is locked or not.

    /**
     * Creates an instance of MyReentrantLock.
     */
    public MyReentrantLock() {
        this.lockingThread = null;
        this.lockCounter = 0;
        this.isLocked = new AtomicBoolean();
    }

    /**
     * Acquires the lock.
     */
    @Override
    public void acquire() {
        if (lockCounter > 0 && lockingThread == Thread.currentThread()) // Current locking thread trying to acquire lock again.
        {
            lockCounter++;
        }
        else {
            while (!(isLocked.compareAndSet(false, true))) // Lock is acquired by another thread.
            {
                try {
                    Thread.sleep(10); //Suspends current thread untill lock is available.
                } catch (InterruptedException e) { }
            }
            lockingThread = Thread.currentThread();
            lockCounter++;
        }
    }

    /**
     * Tries to acquire the lock, if lock is available - acquire it.
     * @return true if lock can be acquired, false otherwise.
     */
    @Override
    public boolean tryAcquire() {
        if (isLocked.get())
            return false;
        isLocked.set(true);
        lockingThread = Thread.currentThread();
        return true;
    }

    /**
     * Releases the lock.
     * @throws IllegalReleaseAttempt if trying to release unlocked lock, or if non-locking thread is trying to release the lock.
     */
    @Override
    public void release() {
        if (!isLocked.get() || Thread.currentThread() != lockingThread)
            throw new IllegalReleaseAttempt();

        else {
            lockCounter--;
            if (lockCounter == 0) {
                lockingThread = null;
                isLocked.set(false);
            }
        }
    }

    /**
     * Closes this resource.
     */
    @Override
    public void close() {
        release();
    }
}
