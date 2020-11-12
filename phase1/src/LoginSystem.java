import java.util.ArrayList;
import java.util.Scanner;

/**
 * The login system.
 */
public class LoginSystem implements IController {
    private final UserManager userMan;
    private final LoginSystemPresenter lp;

    /**
     * Allows users to login to their account or create one.
     * @param userManager the user manager use case class
     */
    public LoginSystem(UserManager userManager) {
        this.userMan = userManager;
        this.lp = new LoginSystemPresenter();
    }

    public int run() {
        // creates input obj and asks user if they want to login or create account
        Scanner input = new Scanner(System.in);
        // DUMMY ACCOUNT FOR TESTING
        this.userMan.createAttendeeAccount("kerry");

        this.lp.LoginCreateActPrompt();
        String decision = input.nextLine();

        // if user wants to login
        if (decision.equals("Login")) {
            this.lp.enterUsernamePrompt();
            String userName = input.nextLine();
            // keeps asking for until matching one found
            while (!this.userMan.setCurrentUserFromUserName(userName)) {
                this.lp.goBackOptionPrompt();
                userName = input.nextLine();
                if (userName.equals("back")) {
                    return Definitions.LOGIN_SYSTEM;
                }
            }

            String accountType = this.userMan.userType(userName);
            this.lp.displayLoginSuccess(accountType, userName);
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

            while (existingUsers.contains(accountName) || accountName.equals("back") ||
                    accountName.equals("all")) {
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
            return Definitions.LOGIN_SYSTEM;
        }
        else {
            this.lp.invalidInputPrompt();
        }
        return Definitions.LOGIN_SYSTEM;
    }
}
