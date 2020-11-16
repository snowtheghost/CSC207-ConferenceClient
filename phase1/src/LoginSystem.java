import java.util.ArrayList;
import java.util.Scanner;

/**
 * The login system.
 */
public class LoginSystem implements IController {
    private final UserManager userMan;
    private final LoginSystemPresenter lp;
    private final Scanner input;

    /**
     * Allows users to login to their account or create one.
     * @param userManager the user manager use case class
     */
    public LoginSystem(UserManager userManager) {
        this.userMan = userManager;
        this.lp = new LoginSystemPresenter();
        this.input = new Scanner(System.in);
    }
    /**
     * Initial prompt telling user to login, create account, or quit.
     */
    public int run() {
        this.lp.LoginCreateActPrompt();
        String decision = input.nextLine();

        // Check user decision and act accordingly
        switch(decision.toLowerCase()) {
            case "login":
                return loginToAccount();
            case "create account":
                return createAccount();
            case "quit":
                return Definitions.QUIT;
            default:
                this.lp.invalidInputPrompt();
        }
        // If the input is invalid, remain in this state.
        return Definitions.REMAIN_IN_STATE;
    }

    /**
     * Creates a user account with given credentials.
     * @return an integer representing a state command.
     */
    public int createAccount() {
        ArrayList<String> validTypes = new ArrayList<>();
        validTypes.add("speaker");
        validTypes.add("organizer");
        validTypes.add("attendee");
        this.lp.enterActTypePrompt();
        String accountType = input.nextLine();
        // keep asking for account type until valid account type given
        while (!validTypes.contains(accountType)) {
            this.lp.invalidActTypePrompt(accountType);
            accountType = input.nextLine();
        }
        // keep asking for account name until unique account name given
        this.lp.enterActNamePrompt();
        String accountName = input.nextLine();
        ArrayList<String> existingUsers = this.userMan.getUsernames();

        while (existingUsers.contains(accountName) || accountName.equalsIgnoreCase("back") ||
                accountName.equalsIgnoreCase("all") || accountName.equalsIgnoreCase("quit")) {
            this.lp.invalidActNamePrompt();
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
        this.lp.displaySuccCreation(accountType, accountName);
        return Definitions.REMAIN_IN_STATE;
    }

    /**
     * Attempts to login a user based on the given credentials.
     * @return an integer representing a state command.
     */
    public int loginToAccount() {
        this.lp.enterUsernamePrompt();
        String userName = input.nextLine();
        // keeps asking for until matching one found
        while (!this.userMan.setCurrentUserFromUserName(userName)) {
            this.lp.goBackOptionPrompt();
            userName = input.nextLine();
            if (userName.equals("back")) {
                return Definitions.REMAIN_IN_STATE;
            }
        }

        String accountType = this.userMan.userType(userName);
        this.lp.displayLoginSuccess(accountType, userName);
        switch (accountType) {
            case "speaker":
                return 2;
            case "attendee":
                return 0;
            case "organizer":
                return 1;
        }
        return Definitions.REMAIN_IN_STATE;
    }
}
