import java.util.Scanner;

/**
 * Interface based controller example.
 * @author Zachariah Vincze
 */
public class AltLoginSystem implements IController {
    private final UserManager userManager;
    private String userInput;

    public AltLoginSystem(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public int run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a command (quit/statechange): ");
        userInput = scanner.nextLine();
        switch (userInput) {
            case "quit":
                return Definitions.QUIT_APP;
            case "statechange":
                return Definitions.ORGANIZER_PANEL;
            default:
                break;
        }
        return Definitions.LOGIN_SYSTEM;
    }
}
