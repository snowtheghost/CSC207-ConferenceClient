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
        // DUMMY ACCOUNT FOR TESTING
        this.userMan.createAttendeeAccount("kerry");

        System.out.println("\"Login\" or \"Create account\"?");
        String decision = input.nextLine();

        // if user wants to login
        if (decision.equals("Login")) {
            System.out.println("Enter account name");
            String userName = input.nextLine();
            // keeps asking for until matching one found
            while (!this.userMan.setCurrentUserFromUserName(userName)) {
                System.out.println("Or, if you wish to return, type 'back' to go back");
                userName = input.nextLine();
                if (userName.equals("back")) {
                    return Definitions.LOGIN_SYSTEM;
                };
            }

            String accountType = this.userMan.userType(userName);
            System.out.println("Loggined into " + accountType + " account, username: " + userName);
            switch (accountType) {
                case "speaker":
                    return Definitions.SPEAKER_PANEL;
                case "attendee":
                    return Definitions.ATTENDEE_PANEL;
                case "organizer":
                    return Definitions.ORGANIZER_PANEL;
            }
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
            ArrayList<String> existingUsers = this.userMan.getUsernames();
            while (existingUsers.contains(accountName) || accountName.equals("back")) {
                System.out.println("Account name taken or invalid name, please try a different name.");
                accountName = input.nextLine();
            }
            switch (accountType) {
                case "speaker":
                    this.userMan.createSpeakerAccount(accountName);
                    break;
                case "organizer":
                    this.userMan.createOrganizerAccount(accountName);
                    break;
                case "attendee":
                    this.userMan.createAttendeeAccount(accountName);
                    break;
            }
            System.out.println("Successfully created " + accountType + " account, username: " +
                    accountName);
            return Definitions.LOGIN_SYSTEM;
        }
        else {
            System.out.println("invalid input");
        }
        return Definitions.LOGIN_SYSTEM;
    }
}
