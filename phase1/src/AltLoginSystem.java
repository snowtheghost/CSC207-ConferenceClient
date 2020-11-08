import java.util.Scanner;

/**
 * Interface based controller example.
 * @author Zachariah Vincze
 */
public class AltLoginSystem implements IController {
    private UserManager userManager;
    private boolean quitting;
    private boolean changeState;
    private int newState;
    private String userInput;

    public AltLoginSystem(UserManager userManager) {
        this.userManager = userManager;
        this.quitting = false;
        this.changeState = false;
        this.newState = 0;
    }

    @Override
    public void run() {
        reset();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a command (quit/statechange): ");
        userInput = scanner.nextLine();
        switch (userInput) {
            case "quit":
                this.quitting = true;
                break;
            case "statechange":
                this.changeState = true;
                System.out.print("Choose new state: ");
                this.newState = scanner.nextInt();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isQuitting() {
        return this.quitting;
    }

    @Override
    public boolean isChangingState() {
        return this.changeState;
    }

    @Override
    public int getNewState() {
        return this.newState;
    }

    /**
     * This must be run at the start of the
     * run method to ensure that the variables
     * don't remain the same if we are switching into
     * this state from another state.
     */
    private void reset() {
        this.quitting = false;
        this.changeState = false;
    }
}
