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
import javafx.scene.layout.GridPane;

/**
 * @author Template: Justin Chan
 */

public class OrganizerScene implements IScene{
    OrganizerFilter filter;
    OrganizerPresenter presenter;

    public OrganizerScene(OrganizerFilter filter, OrganizerPresenter presenter) {
        this.filter = filter;
        this.presenter = presenter;
    }

    public void setScene() {
        // Scenes
        Scene mainScene;
        GridPane reMenu = new GridPane();
        GridPane createRoomMenu = new GridPane();
        ListView<String> viewRoomsList = new ListView<>();
        GridPane speakersMenu = new GridPane();
        GridPane createSpeakersMenu = new GridPane();
        ListView<String> viewSpeakersList = new ListView<>();

        // Layout and scene for Full View
        mainScene = new Scene(main, x, y);

        // Layout and scene for Menu
        topMenu.setSpacing(10);

        // Layout and scene for Room/Events
        reMenu.setVgap(2.5);
        reMenu.setHgap(2.5);
        createRoomMenu.setVgap(2.5);
        createRoomMenu.setHgap(2.5);

        // Layout and scene for Speakers
        speakersMenu.setVgap(2.5);
        speakersMenu.setHgap(2.5);
        createSpeakersMenu.setVgap(2.5);
        createSpeakersMenu.setHgap(2.5);


        // Elements for the Main Menu
        Button reButton = new Button(presenter.reButtonText());
        reButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(reMenu);
                MainView.getStage().setTitle(presenter.reSceneTitle());
            }
        });

        Button speakersMenuButton = new Button(presenter.speakersMenuButtonText());
        speakersMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(speakersMenu);
                MainView.getStage().setTitle(presenter.speakersMenuSceneTitle());
            }
        });

        Button logoutButton = new Button(presenter.logoutButtonText());
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainView.setLoginScene();
            }
        });

        // Elements for the Room/Events Menu
        Button createRoomButton = new Button(presenter.createRoomButtonText());
        createRoomButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(createRoomMenu);
                MainView.getStage().setTitle(presenter.createRoomSceneTitle());
            }
        });

        Button viewRoomsButton = new Button(presenter.viewRoomsButtonText());
        viewRoomsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(viewRoomsList);
                MainView.getStage().setTitle(presenter.viewRoomsSceneTitle());

                ObservableList<String> roomsList = FXCollections.observableArrayList();
                roomsList.addAll(presenter.getRooms());
                viewRoomsList.setItems(roomsList);
            }
        });

        Label roomCapacityLabel = new Label(presenter.roomCapacityPrompt());
        TextField roomCapacityInput = new TextField();
        Label createRoomSuccess = new Label(presenter.createRoomStatus(true));
        Label createRoomFailure = new Label(presenter.createRoomStatus(false));
        Button createNewRoomButton = new Button(presenter.createButtonText());
        createNewRoomButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createRoomMenu.getChildren().remove(createRoomSuccess);
                createRoomMenu.getChildren().remove(createRoomFailure);
                if (filter.inputRoomCapacity(roomCapacityInput.getText())) {
                    createRoomSuccess.setText(presenter.createRoomStatus(true));
                    createRoomMenu.add(createRoomSuccess, 5, 7, 2, 1);
                } else {
                    createRoomFailure.setText(presenter.createRoomStatus(false));
                    createRoomMenu.add(createRoomFailure, 5, 7, 2, 1);
                }
            }
        });

        // Elements for Speaker Menu
        Button createSpeakerButton = new Button(presenter.createSpeakerButtonText());
        createSpeakerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(createSpeakersMenu);
                MainView.getStage().setTitle(presenter.createSpeakerSceneTitle());
            }
        });

        Button viewSpeakersButton = new Button(presenter.viewSpeakersButtonText());
        viewSpeakersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(viewSpeakersList);
                MainView.getStage().setTitle(presenter.viewSpeakersSceneTitle());

                ObservableList<String> speakersList = FXCollections.observableArrayList();
                speakersList.addAll(presenter.getSpeakerNames());
                viewSpeakersList.setItems(speakersList);
            }
        });

        Label nameLabel = new Label(presenter.usernamePrompt());
        TextField nameInput = new TextField();
        Label createSpeakerSuccess = new Label(presenter.createSpeakerStatus(true));
        Label createSpeakerFailure = new Label(presenter.createSpeakerStatus(false));
        Button createSpeakerUsernameButton = new Button(presenter.createButtonText());
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

        // Add elements to Room/Event Menu
        reMenu.add(createRoomButton, 5, 5);
        createRoomMenu.add(roomCapacityLabel, 5, 5);
        createRoomMenu.add(roomCapacityInput, 6, 5);
        createRoomMenu.add(createNewRoomButton, 7, 5);
        reMenu.add(viewRoomsButton, 6, 5);

        // Add buttons to Speaker Menu
        speakersMenu.add(createSpeakerButton, 5, 5);
        createSpeakersMenu.add(nameLabel, 5, 5);
        createSpeakersMenu.add(nameInput, 6, 5);
        createSpeakersMenu.add(createSpeakerUsernameButton, 7, 5);
        speakersMenu.add(viewSpeakersButton, 6, 5);

        MainView.getStage().setScene(mainScene);
        MainView.getStage().setTitle(presenter.mainSceneTitle());
    }
}
