package com.group0179;

import com.group0179.PresenterFactory.AttendeePresenterFactory;
import com.group0179.PresenterFactory.LoginPresenterFactory;
import com.group0179.PresenterFactory.OrganizerPresenterFactory;
import com.group0179.PresenterFactory.SpeakerPresenterFactory;
import com.group0179.controllers.AttendeePresenter;
import com.group0179.controllers.AutofillController;
import com.group0179.controllers.LoginController;
import com.group0179.controllers.SpeakerPresenterController;
import com.group0179.entities.Organizer;
import com.group0179.filters.*;
import com.group0179.scenes.*;
import com.group0179.presenters.*;
import com.group0179.gateways.*;
import com.group0179.use_cases.*;
import javafx.application.Application;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

        // Add some dummy variables for now
        // TODO: Remove this once we're finished testing
        userManager.createAttendeeAccount("a", false);
        userManager.createSpeakerAccount("DummySpeaker");
        userManager.createOrganizerAccount("DummyOrganizer");
        roomManager.newRoom(10);
        roomManager.newEvent("DummyEvent", "DummySpeaker",
                new GregorianCalendar(2020, 12, 25, 12, 0),
                new GregorianCalendar(2020, 12, 25, 14, 00),
                0, userManager,
                10);

        // Input filters
        LoginFilter loginFilter = new LoginFilter(userManager, roomManager, messageManager);
        SpeakerFilter speakerFilter = new SpeakerFilter(userManager, roomManager, messageManager);
        OrganizerFilter organizerFilter = new OrganizerFilter(userManager, roomManager, messageManager);

        // Presenters
        ILoginPresenter loginPresenter = new LoginPresenterEN(); // TODO: Figure out how to switch presenters.
        //SpeakerPresenter speakerPresenter = new SpeakerPresenter(userManager, roomManager, messageManager);
        OrganizerPresenterEN organizerPresenter = new OrganizerPresenterEN(userManager, roomManager, messageManager);

        //PresenterFactory
        LoginPresenterFactory loginPresenterFactory = new LoginPresenterFactory();
        OrganizerPresenterFactory organizerPresenterFactory = new OrganizerPresenterFactory(userManager, roomManager, messageManager);
        AttendeePresenterFactory attendeePresenterFactory = new AttendeePresenterFactory();
        SpeakerPresenterFactory speakerPresenterFactory = new SpeakerPresenterFactory();

        // Controllers
        LoginController loginController = new LoginController(userManager);
        AttendeePresenter attendeePresenter = new AttendeePresenter(userManager, roomManager, messageManager);
        SpeakerPresenterController speakerPresenterController = new SpeakerPresenterController(userManager, roomManager, messageManager);
        AutofillController autofill = new AutofillController(userManager, roomManager, messageManager);

        // Scene Setup
        LoginScene loginScene = new LoginScene(loginFilter, loginPresenterFactory, loginController);
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
