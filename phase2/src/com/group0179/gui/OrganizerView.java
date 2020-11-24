package com.group0179.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import com.group0179.gui_bridge.OrganizerFilter;
import com.group0179.gui_bridge.OrganizerPresenter;

/**
 * @author Template: Justin Chan
 */

public class OrganizerView extends Application implements IView{
    static OrganizerFilter filter;
    static OrganizerPresenter op;

    Stage window;
    Scene mainPanel;
    int x = 400;
    int y = 300; // window dimensions

    public static void main(String[] args) {
        launch(args);
    }

    static public void setup(OrganizerFilter filter, OrganizerPresenter op) {
        OrganizerView.filter = filter;
        OrganizerView.op = op;
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;

        // Layout and scene for Full View
        BorderPane main = new BorderPane();
        mainPanel = new Scene(main, x, y);

        // Layout and scene for Menu
        HBox topMenu = new HBox();
        topMenu.setSpacing(10);

        // Layout and scene for Rooms/Events
        GridPane reMenu = new GridPane();

        // Layout and scene for Speakers
        GridPane speakersMenu = new GridPane();
        speakersMenu.setVgap(2.5);
        speakersMenu.setHgap(2.5);
        GridPane createSpeakersMenu = new GridPane();
        createSpeakersMenu.setVgap(2.5);
        createSpeakersMenu.setHgap(2.5);
        ListView<String> viewSpeakersList = new ListView<>();

        // Elements for the Main Menu
        Button reButton = new Button ("Rooms/Events");
        reButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(reMenu);
                window.setTitle("Organizer Panel: Rooms and Events");
            }
        });

        Button speakersMenuButton = new Button ("Speakers");
        speakersMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(speakersMenu);
                window.setTitle("Organizer Panel: Speakers");
            }
        });


        // Elements for Speaker Menu
        Button createSpeakerButton = new Button ("Create Speaker");
        createSpeakerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(createSpeakersMenu);
                window.setTitle("Organizer Panel: Create Speakers");
            }
        });

        Button viewSpeakersButton = new Button ("View Speakers");
        viewSpeakersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(viewSpeakersList);
                window.setTitle("Organizer Panel: View Speakers");

                ObservableList<String> speakersList = FXCollections.observableArrayList();
                speakersList.addAll(op.getSpeakerNames());
                viewSpeakersList.setItems(speakersList);
            }
        });

        Label nameLabel = new Label("Username: ");
        TextField nameInput = new TextField();

        Label createSpeakerSuccess = new Label("Speaker created successfully.");
        Label createSpeakerFailure = new Label("Username already taken");

        Button createSpeakerUsernameButton = new Button ("Create");
        createSpeakerUsernameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createSpeakersMenu.getChildren().remove(createSpeakerSuccess);
                createSpeakersMenu.getChildren().remove(createSpeakerFailure);
                if (filter.inputNewSpeakerUsername(nameInput.getText())) {
                    createSpeakersMenu.add(createSpeakerSuccess, 5, 7, 2, 1);
                } else {
                    createSpeakersMenu.add(createSpeakerFailure, 5, 7, 2, 1);
                }
            }
        });


        // Set initial scene for Main Panel
        main.setTop(topMenu);

        // Add buttons to Top Menu
        topMenu.getChildren().addAll(reButton, speakersMenuButton);

        // Add buttons to Speaker Menu
        speakersMenu.add(createSpeakerButton, 5, 5);
        createSpeakersMenu.add(nameLabel, 5, 5);
        createSpeakersMenu.add(nameInput, 6, 5);
        createSpeakersMenu.add(createSpeakerUsernameButton, 7, 5);
        speakersMenu.add(viewSpeakersButton, 6, 5);


        // Setup and Start Initial Scene: Main Menu
        window.setScene(mainPanel);
        window.setTitle("Organizer Panel: Main Menu");
        window.show();

        // Exit script
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                windowEvent.consume();
                closeProgram();
            }
        });
    }

    private void closeProgram() {
        // TODO: Exit code goes here
        window.close();
    }
}
