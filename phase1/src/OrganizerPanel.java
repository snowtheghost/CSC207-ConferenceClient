public class OrganizerPanel implements IController {
    private boolean quitting = false;

    @Override
    public void run() {
        System.out.print("This is the OrganizerPanel");
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
