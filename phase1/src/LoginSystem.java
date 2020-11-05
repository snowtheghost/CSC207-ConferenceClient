/**
 * The login system.
 */
public class LoginSystem {
    private UserManager userMan;
    private AttendeePanel attenPanel;
    private OrganizerPanel orgPanel;
    private SpeakerPanel spPanel;

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
