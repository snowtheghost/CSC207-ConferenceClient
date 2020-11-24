package com.group0179;

import com.group0179.filters.*;
import com.group0179.scenes.*;
import com.group0179.presenters.*;
import com.group0179.gateways.*;
import com.group0179.use_cases.*;
import javafx.application.Application;

/**
 * Application entrypoint
 * @author Justin Chan
 */

public class AppMain {
    public static void main(String[] args) {
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

        // Scene Setup
        LoginScene loginScene = new LoginScene(loginFilter, loginPresenter);
        OrganizerScene organizerScene = new OrganizerScene(organizerFilter, organizerPresenter);
        AttendeeScene attendeeScene = new AttendeeScene(attendeeFilter, attendeePresenter);
        SpeakerScene speakerScene = new SpeakerScene(speakerFilter, speakerPresenter);

        // Set up MainView and launch
        MainView.setup(loginScene, organizerScene, attendeeScene, speakerScene);
        Application.launch(MainView.class);
    }
}
