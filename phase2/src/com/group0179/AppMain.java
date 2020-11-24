package com.group0179;

import com.group0179.controllers.AttendeePanel;
import com.group0179.controllers.LoginSystem;
import com.group0179.controllers.OrganizerPanel;
import com.group0179.controllers.SpeakerPanel;
import com.group0179.gateways.MessageManagerGateway;
import com.group0179.gateways.RoomManagerGateway;
import com.group0179.gateways.UserManagerGateway;
import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

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

        // Use Cases (deserialized from external file)
        UserManager userManager = userManagerGateway.read("usermanager.ser");
        RoomManager roomManager = roomManagerGateway.read("roommanager.ser");
        MessageManager messageManager = messageManagerGateway.read("messagemanager.ser");

        // Helper classes
        InputFilter inputFilter = new InputFilter(userManager, roomManager);

        // Controllers
        LoginSystem loginSystem = new LoginSystem(userManager);
        AttendeePanel attendeePanel = new AttendeePanel(userManager, messageManager, roomManager, inputFilter);
        OrganizerPanel organizerPanel = new OrganizerPanel(userManager, roomManager, messageManager, inputFilter, loginSystem);
        SpeakerPanel speakerPanel = new SpeakerPanel(userManager, messageManager, roomManager);

        // Construct a MenuTree using the initialized controllers
        MenuTree attendeeMenu = new MenuTree(attendeePanel);
        MenuTree organizerMenu = new MenuTree(organizerPanel);
        MenuTree speakerMenu = new MenuTree(speakerPanel);

        MenuTree mainMenu = new MenuTree(loginSystem);
        mainMenu.addChild(attendeeMenu);
        mainMenu.addChild(organizerMenu);
        mainMenu.addChild(speakerMenu);
        //stop manually adding these accounts like below. It messes up the program.
        //just properly create the account from the console please and thank you
        //userManager.createAttendeeAccount("kerry", false);
        //userManager.createOrganizerAccount("justin");
        //userManager.createSpeakerAccount("zach");

/*
Feel free to test the timer. Pretty sure it works fine tho. There's only one deontay wilder
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        TimeStatistics timeStatistics = new TimeStatistics();
        while (flag) {
            timeStatistics.commenceTiming();
            while(!scanner.hasNextInt()) {
                System.out.println("I order u to input a valid int");
                scanner.nextLine();
            }
            System.out.println(scanner.nextInt());
            System.out.println("Complete");
            flag = false;
        }
        timeStatistics.concludeTiming();
        System.out.println("Time stamp" + timeStatistics.getTimeStamp());
        System.out.println("loggedtime: " + timeStatistics.getTimeLoggedInAsHours());
 */

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
