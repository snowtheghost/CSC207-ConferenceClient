package com.group0179.cli;

import com.group0179.cli.controllers_cli.AttendeePanelCLI;
import com.group0179.cli.controllers_cli.LoginSystemCLI;
import com.group0179.cli.controllers_cli.OrganizerPanelCLI;
import com.group0179.cli.controllers_cli.SpeakerPanelCLI;
import com.group0179.gateways.MessageManagerGateway;
import com.group0179.gateways.RoomManagerGateway;
import com.group0179.gateways.UserManagerGateway;
import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

import java.io.IOException;

/**
 * The app's entrypoint.
 * @author Zachariah Vincze
 */
public class AppMainCLI {

    public static void main(String[] args) {
        boolean applicationRunning = true;

        // Initialize all required components of the application.
        // Gateways
        MessageManagerGateway messageManagerGateway= new MessageManagerGateway();
        UserManagerGateway userManagerGateway = new UserManagerGateway();
        RoomManagerGateway roomManagerGateway = new RoomManagerGateway();

        // Use Cases (deserialized from external file)
        UserManager userManager = userManagerGateway.read("usermanager.ser");
        RoomManager roomManager = roomManagerGateway.read("roommanager.ser");
        MessageManager messageManager = messageManagerGateway.read("messagemanager.ser");

        // Helper classes
        InputFilterCLI inputFilter = new InputFilterCLI(userManager, roomManager);

        // Controllers
        LoginSystemCLI loginSystem = new LoginSystemCLI(userManager);
        AttendeePanelCLI attendeePanel = new AttendeePanelCLI(userManager, messageManager, roomManager, inputFilter);
        OrganizerPanelCLI organizerPanel = new OrganizerPanelCLI(userManager, roomManager, messageManager, inputFilter, loginSystem);
        SpeakerPanelCLI speakerPanel = new SpeakerPanelCLI(userManager, messageManager, roomManager);

        // Construct a MenuTree using the initialized controllers
        MenuTreeCLI attendeeMenu = new MenuTreeCLI(attendeePanel);
        MenuTreeCLI organizerMenu = new MenuTreeCLI(organizerPanel);
        MenuTreeCLI speakerMenu = new MenuTreeCLI(speakerPanel);

        MenuTreeCLI mainMenu = new MenuTreeCLI(loginSystem);
        mainMenu.addChild(attendeeMenu);
        mainMenu.addChild(organizerMenu);
        mainMenu.addChild(speakerMenu);
        userManager.createAttendeeAccount("kerry", false);
        userManager.createOrganizerAccount("justin");
        userManager.createSpeakerAccount("zach");

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
                    // Switches to a given child state.
                    // Ensure that the requested state exists. If it does not, remain in the same state.
                    if (mainMenu.getChild(command) != null) {
                        mainMenu = mainMenu.getChild(command);
                    }
                    break;
            }
        }

        // Attempt to serialize all Use Case data to external file.
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
