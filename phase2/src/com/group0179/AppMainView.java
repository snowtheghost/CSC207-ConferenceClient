package com.group0179;

import com.group0179.gui.*;
import com.group0179.gui_bridge.*;
import com.group0179.gateways.*;
import com.group0179.gui_bridge.*;
import com.group0179.use_cases.*;

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
        LoginFilter loginFilter = new LoginFilter(userManager, roomManager, messageManager);
        LoginPresenter loginPresenter = new LoginPresenter(userManager, roomManager, messageManager);
        AttendeeFilter attendeeFilter = new AttendeeFilter(userManager, roomManager, messageManager);
        AttendeePresenter attendeePresenter = new AttendeePresenter(userManager, roomManager, messageManager);
        SpeakerFilter speakerFilter = new SpeakerFilter(userManager, roomManager, messageManager);
        SpeakerPresenter speakerPresenter = new SpeakerPresenter(userManager, roomManager, messageManager);
        OrganizerFilter organizerFilter = new OrganizerFilter(userManager, roomManager, messageManager);
        OrganizerPresenter organizerPresenter = new OrganizerPresenter(userManager, roomManager, messageManager);

        // View Setup
        LoginView.setup(loginFilter, loginPresenter);
        AttendeeView.setup(attendeeFilter, attendeePresenter);
        SpeakerView.setup(speakerFilter, speakerPresenter);
        OrganizerView.setup(organizerFilter, organizerPresenter);


        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(OrganizerView.class);
            }
        }.start();
    }
}
