import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The app's entrypoint.
 * @author Zachariah Vincze
 */
public class AppMain {

    public static void main(String[] args) {
        boolean applicationRunning = true;

        // Initialize all required components of the application.
        // Gateways
        MessageManagerGateway messageManagerGateway= new MessageManagerGateway();
        UserManagerGateway userManagerGateway = new UserManagerGateway();
        RoomManagerGateway roomManagerGateway = new RoomManagerGateway();

        // Use Cases
        UserManager userManager = userManagerGateway.read("usermanager.ser");
        RoomManager roomManager = roomManagerGateway.read("roommanager.ser");
        MessageManager messageManager = messageManagerGateway.read("messagemanager.ser");

        InputFilter inputFilter = new InputFilter(userManager, roomManager);

        // Controllers
        LoginSystem loginSystem = new LoginSystem(userManager);
        AttendeePanel attendeePanel = new AttendeePanel(userManager, messageManager, roomManager, inputFilter);
        OrganizerPanel organizerPanel = new OrganizerPanel(userManager, roomManager);
        SpeakerPanel speakerPanel = new SpeakerPanel(userManager, messageManager, roomManager);

        // Construct a MenuTree using the initialized controllers
        MenuTree attendeeMenu = new MenuTree(attendeePanel);
        MenuTree organizerMenu = new MenuTree(organizerPanel);
        MenuTree speakerMenu = new MenuTree(speakerPanel);

        MenuTree mainMenu = new MenuTree(loginSystem);
        mainMenu.addChild(attendeeMenu);
        mainMenu.addChild(organizerMenu);
        mainMenu.addChild(speakerMenu);


        while (applicationRunning) {
            int command = mainMenu.run();
            switch (command) {
                case -3:
                    // Quits the application.
                    applicationRunning = false;
                    break;
                case -2:
                    // Ensure that there exists parent node.
                    if (mainMenu.getParent() != null) {
                        mainMenu = mainMenu.getParent();
                    }
                    break;
                case -1:
                    // Does nothing (remains in the same state)
                    break;
                default:
                    // Ensure that the requested state exists.
                    if (mainMenu.getChild(command) != null) {
                        mainMenu = mainMenu.getChild(command);
                    }
                    break;
            }
        }

        // Attempt to write Use Case data to external files.
        try {
            userManagerGateway.write(userManager, "usermanager.ser");
            roomManagerGateway.write(roomManager, "roommanager.ser");
            messageManagerGateway.write(messageManager, "messagemanager.ser");
        } catch (IOException e) {
            System.out.println("Unable to write manager data to file.");
            e.printStackTrace();
        }

    }
}
