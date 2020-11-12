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

        // Gateways
        MessageManagerGateway messageManagerGateway= new MessageManagerGateway();
        UserManagerGateway userManagerGateway = new UserManagerGateway();
        RoomManagerGateway roomManagerGateway = new RoomManagerGateway();

        UserManager userManager = userManagerGateway.read("usermanager.ser");
        RoomManager roomManager = roomManagerGateway.read("roommanager.ser");
        MessageManager messageManager = messageManagerGateway.read("messagemanager.ser");
        InputFilter inputFilter = new InputFilter(userManager, roomManager);

        LoginSystem loginSystem = new LoginSystem(userManager);
        AttendeePanel attendeePanel = new AttendeePanel(userManager, messageManager, roomManager, inputFilter);
        OrganizerPanel organizerPanel = new OrganizerPanel(userManager, roomManager);
        SpeakerPanel speakerPanel = new SpeakerPanel(userManager, messageManager, roomManager);

        // Begin constructing the menu
        MenuTree attendeeMenu = new MenuTree(attendeePanel);
        MenuTree organizerMenu = new MenuTree(organizerPanel);
        MenuTree speakerMenu = new MenuTree(speakerPanel);

        MenuTree loginMenu = new MenuTree(loginSystem);
        loginMenu.addChild(attendeeMenu);
        loginMenu.addChild(organizerMenu);
        loginMenu.addChild(speakerMenu);


        while (applicationRunning) {
            int command = loginMenu.run();
            switch (command) {
                case -3:
                    applicationRunning = false;
                    break;
                case -2:
                    loginMenu = loginMenu.getParent();
                    break;
                case -1:
                    break;
                default:
                    loginMenu = loginMenu.getChild(command);
                    break;
            }
        }

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
