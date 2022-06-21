public class MyReentrantLock implements Lock {
    private Thread lockingThread; // null --> unlocked.
    private int lockCounter; // 0 --> unlocked.

    public MyReentrantLock() {
        this.lockingThread = null;
        this.lockCounter = 0;
    }

    @Override
    public void acquire() {
        while (lockCounter > 0 && lockingThread != Thread.currentThread()) {  // if locked by another thread, wait
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
            lockingThread = Thread.currentThread();
            lockCounter++;
        }
    }

    @Override
    public boolean tryAcquire() {
        if(lockingThread == null && lockCounter == 0)
        {
            acquire();
            return true;
        }
        return false;
    }

    @Override
    public void release() {
        if ((Thread.currentThread() != lockingThread) || (lockCounter == 0)){
            throw new IllegalReleaseAttempt();
        }
        lockCounter = 0;
        lockingThread = null;
        this.notifyAll();  // notify others that lock can be acquired again

    }

    @Override
    public void close() throws Exception {

    }
}
