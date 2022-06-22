/**
 * IllegalReleaseAttempt is an unchecked exception.
 * Thrown if a thread is trying to release unlocked lock, or if non-locking thread is trying to release the lock.
 */
public class IllegalReleaseAttempt extends IllegalMonitorStateException{
    /**
     * Creates an instance of IllegalReleaseAttempt.
     */
    public IllegalReleaseAttempt()
    {
        super();
    }

    /**
     * Constructs a new IllegalReleaseAttempted exception with the specified detail message.
     * @param msg - specified detail message for exception.
     */
    public IllegalReleaseAttempt(String msg)
    {
        super(msg);
    }
}
