import sun.rmi.runtime.Log;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The login system.
 */
public class LoginSystem {
    private final UserManager userMan;
    private final AttendeePanel attenPanel;
    private final OrganizerPanel orgPanel;
    private final SpeakerPanel spPanel;

    public static void main(String[] args) {
        // creates and stores all other controllers
        LoginSystem loginSys = new LoginSystem(new UserManager(), new AttendeePanel(), new OrganizerPanel(),
                new SpeakerPanel());
        // creates input obj and asks user if they want to login or create account
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\"Login\" or \"Create account\"?");
            String decision = input.nextLine();
            // if user wants to login
            if (decision.equals("Login")) {
                System.out.println("Enter account name");
                String userName = input.nextLine();
                List<String> existingUsers = loginSys.userMan.getUserNames();
                // keeps asking for until matching one found
                while (!existingUsers.contains(userName)) {
                    System.out.println("User name not found, please try again.");
                    input.nextLine();
                }
                //loginSys.userMan.setCurrentUser(userName);

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
                boolean success = loginSys.attemptCreateAccount(accountName, accountType);
                while (!success) {
                        System.out.println("Username taken, please try a different name:");
                        accountName = input.nextLine();
                        success = loginSys.attemptCreateAccount(accountName, accountType);
                    }
                System.out.println("Successfully created " + accountType + "account, username: " +
                        accountName);
            } else {
                System.out.println("invalid input");
            }
        }
    }
    /**
     * Allows users to login to their account or create one.
     * @param userMan the user manager use case class
     * @param attenPanel the attendee controller class
     * @param orgPanel the organizer controller class
     * @param spPanel the organizer controller class
     */
    public LoginSystem (UserManager userMan, AttendeePanel attenPanel, OrganizerPanel orgPanel,
                        SpeakerPanel spPanel) {
        this.userMan = userMan;
        this.attenPanel = attenPanel;
        this.orgPanel = orgPanel;
        this.spPanel = spPanel;
    }

    /**
     * Returns true if user can login using accountName, false otherwise.
     * @param accountName the string representing a possible account name
     */
    public Boolean login(String accountName) {
        return false;
    }

    /**
     * Returns true if accountName not taken and account can be
     * created, false otherwise.
     * @param accountName the string representing a possible account name
     * @param accountType the string representing a the account type
     */
    private Boolean attemptCreateAccount(String accountName, String accountType) {
        boolean success = false;
        if (accountType.equals("attendee")){
            success = this.userMan.createAttendeeAccount(accountName);
        }
        else if (accountType.equals("organizer")){
            success = this.userMan.createOrganizerAccount(accountName);
        }
        else if (accountType.equals("speaker")){
            success = this.userMan.createSpeakerAccount(accountName);
        }
        return success;
    }

}
