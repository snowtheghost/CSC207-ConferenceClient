import java.io.IOException;

/**
 * The app's entrypoint.
 * @author Zachariah Vincze
 */
public class AppMain {

    public static void main(String[] args) {
        boolean applicationRunning = true;

        // Gateways
        MessageManagerGateway messageManagerGateway= new MessageManagerGateway();
        UserManagerGateway userManagerGateway = new UserManagerGateway();
        RoomManagerGateway roomManagerGateway = new RoomManagerGateway();

        UserManager userManager = userManagerGateway.read("phase1/usermanager.ser");
        RoomManager roomManager = roomManagerGateway.read("phase1/roommanager.ser");
        MessageManager messageManager = messageManagerGateway.read("phase1/messagemanager.ser");
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

        try {
            userManagerGateway.write(userManager, "phase1/usermanager.ser");
            roomManagerGateway.write(roomManager, "phase1/roommanager.ser");
            messageManagerGateway.write(messageManager, "phase1/messagemanager.ser");
        } catch (IOException e) {
            System.out.println("Unable to write manager data to file.");
            e.printStackTrace();
        }

    }
}
