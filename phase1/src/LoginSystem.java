import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The login system.
 */
public class LoginSystem implements IController {
    private final UserManager userMan;

    /**
     * Allows users to login to their account or create one.
     * @param userManager the user manager use case class
     */
    public LoginSystem(UserManager userManager) {
        this.userMan = userManager;
    }

    public int run() {
        // creates input obj and asks user if they want to login or create account
        Scanner input = new Scanner(System.in);

        System.out.println("\"Login\" or \"Create account\"?");
        String decision = input.nextLine();
        // if user wants to login
        if (decision.equals("Login")) {
            System.out.println("Enter account name");
            String userName = input.nextLine();
            // keeps asking for until matching one found
            while (!this.userMan.setCurrentUserFromUserName(userName)) {
                System.out.println("User name not found, please try again.");
                input.nextLine();
            }
            return Definitions.ATTENDEE_PANEL;

        }
        // if they want to create account
        else if (decision.equals("Create account")) {
            ArrayList<String> validTypes = new ArrayList<>();
            validTypes.add("speaker");
            validTypes.add("organizer");
            validTypes.add("attendee");
            System.out.println("Enter account type (speaker/organizer/attendee):");
            String accountType = input.nextLine();
            // keep asking for account type until valid account type given
            while (!validTypes.contains(accountType)) {
                System.out.println(accountType + " is not a valid account type. Please try again");
                accountType = input.nextLine();
            };
            // keep asking for account name until unique account name given
            System.out.println("Enter account name:");
            String accountName = input.nextLine();

            this.userMan.createAttendeeAccount(accountName);
            System.out.println("Successfully created " + "ATTENDEE FOR NOW" + "account, username: " +
                    accountName);
            return Definitions.LOGIN_SYSTEM;
        } else {
            System.out.println("invalid input");
            return Definitions.LOGIN_SYSTEM;
        }

    }
}
