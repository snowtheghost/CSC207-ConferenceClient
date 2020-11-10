/**
 * The app's entrypoint.
 * @author Zachariah Vincze
 */
public class AppMain {

    public static void main(String args[]) {
        boolean applicationRunning = true;

        UserManager userManager = new UserManager();
        RoomManager roomManager = new RoomManager();
        MessageManager messageManager = new MessageManager();

        LoginSystem loginSystem = new LoginSystem(userManager);
        AttendeePanel attendeePanel = new AttendeePanel();
        OrganizerPanel organizerPanel = new OrganizerPanel(userManager, roomManager);
        SpeakerPanel speakerPanel = new SpeakerPanel();
        IController currentController = loginSystem;

        while (applicationRunning) {
            switch(currentController.run()) {
                case Definitions.LOGIN_SYSTEM:
                    currentController = loginSystem;
                    break;
                case Definitions.ATTENDEE_PANEL:
                    currentController = attendeePanel;
                    break;
                case Definitions.ORGANIZER_PANEL:
                    currentController = organizerPanel;
                    break;
                case Definitions.SPEAKER_PANEL:
                    currentController = speakerPanel;
                    break;
                case Definitions.QUIT_APP:
                    applicationRunning = false;
                    break;
            }
        }
    }
}
