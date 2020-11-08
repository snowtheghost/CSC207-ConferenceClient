public class AttendeePanel implements IController {
    private boolean quitting = false;

    @Override
    public void run() {
        System.out.println("This is the AttendeePanel");
        this.quitting = true;
    }

    @Override
    public boolean isQuitting() {
        return this.quitting;
    }

    @Override
    public boolean isChangingState() {
        return false;
    }

    @Override
    public int getNewState() {
        return 0;
    }
}
