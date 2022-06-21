public class IllegalReleaseAttempt extends IllegalMonitorStateException{
    public IllegalReleaseAttempt()
    {
        super();
    }
    public IllegalReleaseAttempt(String msg)
    {
        super(msg);
    }
}
