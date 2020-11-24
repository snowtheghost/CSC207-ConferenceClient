package com.group0179;

import com.group0179.gui.*;
import com.group0179.gui_bridge.InputFilter;
import com.group0179.gateways.*;
import com.group0179.gui_bridge.OrganizerPresenter;
import com.group0179.use_cases.*;
import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * Application entrypoint
 * @author Justin Chan
 */

public class AppMainView {
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
        InputFilter inputFilter = new InputFilter(userManager, roomManager, messageManager);
        OrganizerPresenter organizerPresenter = new OrganizerPresenter(userManager, roomManager, messageManager);

        // View Setup
        LoginView.setup(); // TODO
        AttendeeView.setup(); // TODO
        SpeakerView.setup(); // TODO
        OrganizerView.setup(inputFilter, organizerPresenter);


        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(OrganizerView.class);
            }
        }.start();
    }
}
