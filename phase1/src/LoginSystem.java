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
        Scanner input = new Scanner(System.in);

        // Enter username and press Enter
        System.out.println("\"Login\" or \"Create account\"?");
        String decision = input.nextLine();

        if (decision.equals("login")) {
            System.out.println("To create account, enter account name");
            String userName = input.nextLine();
            // TO DO check if user name exists, if so pass it onto the correct Panel

        }
        else if (decision.equals("create account")) {
            System.out.println("Enter new username name and type");
            String userName = input.nextLine();
            // have a while loop, asking the user for a account name and type
            // until unique account name and type given, then create account
            // in user manager and pass onto correct panel
        }
        else {
            System.out.println("invalid input");
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
