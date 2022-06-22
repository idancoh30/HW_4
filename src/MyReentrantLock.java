import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock {
    private Thread lockingThread; // null --> unlocked.
    private int lockCounter; // 0 --> unlocked.
    private AtomicBoolean isLocked;

    public MyReentrantLock() {
        this.lockingThread = null;
        this.lockCounter = 0;
        this.isLocked = new AtomicBoolean();
    }

    @Override
    public void acquire() {
        if (lockCounter > 0 && lockingThread == Thread.currentThread()) // המנעול נמצא אצלי ואני מנסה לנעול עוד דברים חדשים
        {
            lockCounter++;
        }
        else { // המנעול לא אצלי אבל הוא אצל מישהו אחר או שהמנעול פנוי
            while (!(isLocked.compareAndSet(false, true))) // המנעול אצל מישהו אחר! אני צריך לחכות
            {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {

                }
            }
            lockingThread = Thread.currentThread();
            lockCounter++;
        }
    }

    @Override
    public boolean tryAcquire() {
        if (isLocked.get())
            return false;
        isLocked.set(true);
        lockingThread = Thread.currentThread();
        return true;
    }

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

    @Override
    public void close() {
        release();
    }
}
