public interface IController {
    public void run();
    public boolean isQuitting();
    public boolean isChangingState();
    public int getNewState();
}
