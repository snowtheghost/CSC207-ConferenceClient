/**
 * The app's entrypoint.
 * @author Zachariah Vincze
 */
public class AppMain {

    public static void main(String[] args) {
        boolean applicationRunning = true;

        UserManager userManager = new UserManager();
        RoomManager roomManager = new RoomManager();
        MessageManager messageManager = new MessageManager();
        InputFilter inputFilter = new InputFilter(userManager, roomManager);

        LoginSystem loginSystem = new LoginSystem(userManager);
        AttendeePanel attendeePanel = new AttendeePanel(userManager, messageManager, roomManager, inputFilter);
        OrganizerPanel organizerPanel = new OrganizerPanel(userManager, roomManager);
        SpeakerPanel speakerPanel = new SpeakerPanel(userManager, messageManager, roomManager);
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
