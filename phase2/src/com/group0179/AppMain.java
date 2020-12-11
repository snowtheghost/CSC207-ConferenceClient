package com.group0179;

import com.group0179.PresenterFactory.AttendeePresenterFactory;
import com.group0179.PresenterFactory.LoginPresenterFactory;
import com.group0179.PresenterFactory.OrganizerPresenterFactory;
import com.group0179.PresenterFactory.SpeakerPresenterFactory;
import com.group0179.controllers.*;
import com.group0179.scenes.*;
import com.group0179.gateways.*;
import com.group0179.use_cases.*;
import javafx.application.Application;

import java.io.IOException;

/**
 * Application entrypoint
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

        //PresenterFactory
        LoginPresenterFactory loginPresenterFactory = new LoginPresenterFactory();
        OrganizerPresenterFactory organizerPresenterFactory = new OrganizerPresenterFactory(userManager, roomManager, messageManager);
        AttendeePresenterFactory attendeePresenterFactory = new AttendeePresenterFactory();
        SpeakerPresenterFactory speakerPresenterFactory = new SpeakerPresenterFactory();

        // Controllers
        LoginController loginController = new LoginController(userManager);
        AttendeePresenter attendeePresenter = new AttendeePresenter(userManager, roomManager, messageManager);
        OrganizerFilter organizerFilter = new OrganizerFilter(userManager, roomManager, messageManager);
        SpeakerPresenterController speakerPresenterController = new SpeakerPresenterController(userManager, roomManager, messageManager);
        AutofillController autofill = new AutofillController(userManager, roomManager, messageManager);

        // Scene Setup
        LoginScene loginScene = new LoginScene(loginPresenterFactory, loginController);
        OrganizerScene organizerScene = new OrganizerScene(organizerFilter, organizerPresenterFactory, loginController, autofill);
        AttendeeScene attendeeScene = new AttendeeScene(attendeePresenter, loginController, attendeePresenterFactory, autofill);
        SpeakerScene speakerScene = new SpeakerScene(speakerPresenterController, loginController, speakerPresenterFactory, autofill);
        LanguageScene languageScene = new LanguageScene();

        // Set up MainView and launch
        MainView.setup(loginScene, organizerScene, attendeeScene, speakerScene, languageScene);
        Application.launch(MainView.class);

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
