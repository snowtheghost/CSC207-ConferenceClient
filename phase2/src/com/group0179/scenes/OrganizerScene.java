package com.group0179.scenes;

import com.group0179.MainView;
import com.group0179.filters.OrganizerFilter;
import com.group0179.presenters.OrganizerPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * @author Template: Justin Chan
 */

public class OrganizerScene implements IScene{
    OrganizerFilter filter;
    OrganizerPresenter op;

    Scene mainScene;
    GridPane reMenu = new GridPane();
    GridPane speakersMenu = new GridPane();
    GridPane createSpeakersMenu = new GridPane();

    public OrganizerScene(OrganizerFilter filter, OrganizerPresenter op) {
        this.filter = filter;
        this.op = op;
    }

    public void setScene() {
        // Layout and scene for Full View
        mainScene = new Scene(main, x, y);

        // Layout and scene for Menu
        topMenu.setSpacing(10);

        // Layout and scene for Speakers
        speakersMenu.setVgap(2.5);
        speakersMenu.setHgap(2.5);
        createSpeakersMenu.setVgap(2.5);
        createSpeakersMenu.setHgap(2.5);

        // Elements for the Main Menu
        Button reButton = new Button("Rooms/Events");
        reButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(reMenu);
                MainView.getStage().setTitle("Organizer Panel: Rooms and Events");
            }
        });

        Button speakersMenuButton = new Button("Speakers");
        speakersMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(speakersMenu);
                MainView.getStage().setTitle("Organizer Panel: Speakers");
            }
        });

        Button logoutButton = new Button("Log out");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainView.setLoginScene();
            }
        });


        // Elements for Speaker Menu
        Button createSpeakerButton = new Button("Create Speaker");
        createSpeakerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(createSpeakersMenu);
                MainView.getStage().setTitle("Organizer Panel: Create Speakers");
            }
        });

        ListView<String> viewSpeakersList = new ListView<>();
        Button viewSpeakersButton = new Button("View Speakers");
        viewSpeakersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(viewSpeakersList);
                MainView.getStage().setTitle("Organizer Panel: View Speakers");

                ObservableList<String> speakersList = FXCollections.observableArrayList();
                speakersList.addAll(op.getSpeakerNames());
                viewSpeakersList.setItems(speakersList);
            }
        });

        Label nameLabel = new Label("Username: ");
        TextField nameInput = new TextField();
        Label createSpeakerSuccess = new Label("Speaker created successfully.");
        Label createSpeakerFailure = new Label("Username already taken");
        Button createSpeakerUsernameButton = new Button("Create");
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
        topMenu.getChildren().addAll(reButton, speakersMenuButton, logoutButton);

        // Add buttons to Speaker Menu
        speakersMenu.add(createSpeakerButton, 5, 5);
        createSpeakersMenu.add(nameLabel, 5, 5);
        createSpeakersMenu.add(nameInput, 6, 5);
        createSpeakersMenu.add(createSpeakerUsernameButton, 7, 5);
        speakersMenu.add(viewSpeakersButton, 6, 5);

        MainView.getStage().setScene(mainScene);
        MainView.getStage().setTitle("Organizer Panel: Main Menu");
    }
}
