package com.group0179;

import com.group0179.scenes.*;
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
    static LanguageScene languageScene;

    public static void setup(LoginScene loginScene, OrganizerScene organizerScene, AttendeeScene attendeeScene, SpeakerScene speakerScene, LanguageScene languageScene) {
        MainView.loginScene = loginScene;
        MainView.organizerScene = organizerScene;
        MainView.attendeeScene = attendeeScene;
        MainView.speakerScene = speakerScene;
        MainView.languageScene = languageScene;
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

    public static void setLanguageScene() { languageScene.setScene();}

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        loginScene.constructScene();
        attendeeScene.constructScene();
        organizerScene.constructScene();
        speakerScene.constructScene();
        languageScene.constructScene();
        // setOrganizerScene(); // Should start on loginScene
        //setLoginScene();
        setLanguageScene();
        stage.show();
    }
}
