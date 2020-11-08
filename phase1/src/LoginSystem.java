import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The login system.
 */
public class LoginSystem {
    private UserManager userMan;
    private AttendeePanel attenPanel;
    private OrganizerPanel orgPanel;
    private SpeakerPanel spPanel;

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
            if (decision.equals("login")) {
                System.out.println("Enter account name");
                String userName = input.nextLine();
                // TODO: check if user name exists, if so pass it onto the correct Panel
            }
            // if they want to create account
            else if (decision.equals("create account")) {
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
                }
                ;
                // keep asking for account name until unique account name given
                System.out.println("Enter account name:");
                String accountName = input.nextLine();
                while (!loginSys.userMan.createAccount(accountName, false)) {
                    System.out.println("Username taken, please try a different name:");
                    accountName = input.nextLine();
                }
                ;
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
     */
    public Boolean createAccount(String accountName, String accountType) {
        return false;
    }

}
