package com.group0179;

import com.group0179.scenes.AttendeeScene;
import com.group0179.scenes.LoginScene;
import com.group0179.scenes.OrganizerScene;
import com.group0179.scenes.SpeakerScene;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Template: Justin Chan
 */

public class MainView extends Application {
    static Stage stage;
    static LoginScene loginScene;
    static OrganizerScene organizerScene;
    static AttendeeScene attendeeScene;
    static SpeakerScene speakerScene;

    public static void setup(LoginScene loginScene, OrganizerScene organizerScene, AttendeeScene attendeeScene, SpeakerScene speakerScene) {
        MainView.loginScene = loginScene;
        MainView.organizerScene = organizerScene;
        MainView.attendeeScene = attendeeScene;
        MainView.speakerScene = speakerScene;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setLoginScene() {
        loginScene.setScene();
    }

    public static void setOrganizerScene() {
        organizerScene.setScene();
    }

    public static void setAttendeeScene() {
        attendeeScene.setScene();
    }

    public static void setSpeakerScene() {
        speakerScene.setScene();
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        setOrganizerScene(); // Should start on loginScene
        stage.show();
    }
}
