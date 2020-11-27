package com.group0179.scenes;

import com.group0179.MainView;
import com.group0179.filters.OrganizerFilter;
import com.group0179.presenters.OrganizerPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * @author Justin Chan
 */

public class OrganizerScene implements IScene{
    OrganizerFilter filter;
    OrganizerPresenter presenter;
    int currentRoomNumber = 0;

    public OrganizerScene(OrganizerFilter filter, OrganizerPresenter presenter) {
        this.filter = filter;
        this.presenter = presenter;
    }

    /**
     * This method creates and manipulates all GUI related functions under the Organizer's responsibilities.
     * Further documentation of the structure below
     */
    public void setScene() {

        /*
         * Main setup
         */

        Scene mainScene = new Scene(main, x, y);
        topMenu.setSpacing(10); topMenu.setPadding(new Insets(10, 10, 10, 10));
        main.setTop(topMenu);
        MainView.getStage().setScene(mainScene);
        MainView.getStage().setTitle(presenter.mainSceneTitle());


        /*
         * Scenes
         */

        // Rooms & Events Manager
        ListView<String> reManager = new ListView<>();
        HBox reManagerBottomMenu = new HBox(); reManagerBottomMenu.setSpacing(10); reManagerBottomMenu.setPadding(new Insets(10, 10, 10, 10));
            // Create Room
            GridPane createRoomForm = new GridPane(); createRoomForm.setVgap(10); createRoomForm.setHgap(10); createRoomForm.setPadding(new Insets(0, 10, 0, 10));
            Label createRoomSuccess = new Label(presenter.createRoomStatus(true));
            Label createRoomFailure = new Label(presenter.createRoomStatus(false));
            Label roomCapacityPrompt = new Label(presenter.roomCapacityPrompt());
            TextField roomCapacityInput = new TextField();
            // View Events (given Room)
            ListView<String> viewEventList = new ListView<>();
            HBox viewEventListBottomMenu = new HBox(); viewEventListBottomMenu.setSpacing(10); viewEventListBottomMenu.setPadding(new Insets(10, 10, 10, 10));
                // Create Event (given Room)
                GridPane createEventForm = new GridPane(); createEventForm.setVgap(10); createEventForm.setHgap(10); createEventForm.setPadding(new Insets(0, 10, 0, 10));
                HBox createEventFormBottomMenu = new HBox(); createEventFormBottomMenu.setSpacing(10); createEventFormBottomMenu.setPadding(new Insets(10, 10, 10, 10));
                Label createEventTitleFailure = new Label(presenter.createEventTitleStatus());
                Label createEventSpeakerFailure = new Label(presenter.createEventSpeakerStatus());
                Label createEventCapacityFailure = new Label(presenter.createEventCapacityStatus());
                Label createEventDateTimeFailure = new Label(presenter.createEventDateTimeStatus());
                Label createEventSuccess = new Label(presenter.createEventStatus());
                Label createEventTitleLabel = new Label(presenter.createEventTitlePrompt());
                TextField createEventTitleInput = new TextField();
                Label createEventSpeakerLabel = new Label(presenter.createEventSpeakerPrompt());
                TextField createEventSpeakerInput = new TextField();
                Label createEventCapacityLabel = new Label(presenter.createEventCapacityPrompt());
                TextField createEventCapacityInput = new TextField();
                Label createEventDateLabel = new Label(presenter.createEventDatePrompt());
                TextField createEventDateInput = new TextField();
                Label createEventTimeLabel = new Label(presenter.createEventTimePrompt());
                TextField createEventTimeInput = new TextField();

        // Speaker Manager
        ListView<String> speakerManager = new ListView<>();
        HBox speakerManagerBottomMenu = new HBox(); speakerManagerBottomMenu.setSpacing(10); speakerManagerBottomMenu.setPadding(new Insets(10, 10, 10, 10));
            // Create Speaker
            GridPane createSpeakerForm = new GridPane(); createSpeakerForm.setVgap(10); createSpeakerForm.setHgap(10); createSpeakerForm.setPadding(new Insets(0, 10, 0, 10));
            Label createSpeakerSuccess = new Label(presenter.createSpeakerStatus(true));
            Label createSpeakerFailure = new Label(presenter.createSpeakerStatus(false));
            Label createSpeakerNamePrompt = new Label(presenter.usernamePrompt());
            TextField createSpeakerNameInput = new TextField();


        /*
         * Buttons and input process
         */

        // Button that leads from the Top Menu to the Room list (reManager)
        Button reManagerButton = new Button(presenter.reManagerButton());
        reManagerButton.setOnAction(actionEvent -> {
            main.setCenter(reManager);
            main.setBottom(reManagerBottomMenu);
            MainView.getStage().setTitle(presenter.reManagerTitle());
            // Get and add all rooms to the list
            ObservableList<String> roomsList = FXCollections.observableArrayList();
            roomsList.addAll(presenter.getRoomListArray());
            reManager.setItems(roomsList);
        });

            // Button that leads from reManager to the Room creation form (createRoomForm)
            Button createRoomFormButton = new Button(presenter.createRoomFormButtonText());
            createRoomFormButton.setOnAction(actionEvent -> {
                main.setCenter(createRoomForm);
                main.setBottom(emptyBottomMenu);
                MainView.getStage().setTitle(presenter.createRoomFormTitle());
            });

                // Button that creates the Room specified in createRoomForm
                Button createRoomButton = new Button(presenter.createButtonText());
                createRoomButton.setOnAction(actionEvent -> {
                    // Remove previously added messages
                    createRoomForm.getChildren().remove(createRoomSuccess);
                    createRoomForm.getChildren().remove(createRoomFailure);
                    // Check and respond to valid/invalid input
                    if (filter.inputRoomCapacity(roomCapacityInput.getText())) {
                        createRoomSuccess.setText(presenter.createRoomStatus(true));
                        createRoomForm.add(createRoomSuccess, 0, 1, 2, 1);
                    } else {
                        createRoomFailure.setText(presenter.createRoomStatus(false));
                        createRoomForm.add(createRoomFailure, 0, 1, 2, 1);
                    }
                });

            // Button that leads from reManager to viewEventList
            Button viewEventListButton = new Button(presenter.viewEventListButtonText());
            viewEventListButton.setOnAction(actionEvent -> {
                main.setCenter(viewEventList);
                main.setBottom(viewEventListBottomMenu);
                MainView.getStage().setTitle(presenter.viewEventListTitle());
                // Try to form a list of events for the selected room
                try {
                    // Get the currently selected room in the list
                    String room = reManager.getSelectionModel().getSelectedItem();
                    // Add all events in the room to the eventList
                    ObservableList<String> eventList = FXCollections.observableArrayList();
                    eventList.addAll(presenter.getEvents(room));
                    viewEventList.setItems(eventList);
                    // Set the currentRoomNumber
                    currentRoomNumber = presenter.getRoomNumber(room);
                } catch (NullPointerException e) {
                    // "Stay" in current view
                    main.setCenter(reManager);
                    main.setBottom(reManagerBottomMenu);
                    MainView.getStage().setTitle(presenter.reManagerTitle());
                }
            });

                // Button that leads from viewEventList to createEventForm
                Button createEventButton = new Button(presenter.createEventButtonText());
                createEventButton.setOnAction(actionEvent -> {
                    main.setCenter(createEventForm);
                    main.setBottom(createEventFormBottomMenu);
                    MainView.getStage().setTitle(presenter.createEventSceneTitle());
                });

                    // Button that creates an event if the inputs are correct
                    Button createNewEventButton = new Button(presenter.createButtonText());
                    createNewEventButton.setOnAction(actionEvent -> {
                        createEventForm.getChildren().remove(createEventTitleFailure);
                        createEventForm.getChildren().remove(createEventSpeakerFailure);
                        createEventForm.getChildren().remove(createEventCapacityFailure);
                        createEventForm.getChildren().remove(createEventDateTimeFailure);
                        createEventForm.getChildren().remove(createEventSuccess);
                        if (!filter.inputEventTitle(createEventTitleInput.getText())) {
                            createEventForm.add(createEventTitleFailure, 0, 5, 2, 1);
                        } else if (!filter.inputEventSpeaker(createEventSpeakerInput.getText())) {
                            createEventForm.add(createEventSpeakerFailure, 0, 5, 2, 1);
                        } else if (!filter.inputEventCapacity(createEventCapacityInput.getText(), currentRoomNumber)) {
                            createEventForm.add(createEventCapacityFailure, 0, 5, 2, 1);
                        } else if (!filter.inputEventDate(createEventTitleInput.getText(), createEventSpeakerInput.getText(), createEventDateInput.getText(), createEventTimeInput.getText(), createEventCapacityInput.getText(), currentRoomNumber)) {
                            createEventForm.add(createEventDateTimeFailure, 0, 5, 2, 1);
                        } else {
                            createEventForm.add(createEventSuccess, 0, 5, 2, 1);
                        }
                    });

        // Button that leads from Top Menu to speakerManager
        Button speakerManagerButton = new Button(presenter.speakerManagerButtonText());
        speakerManagerButton.setOnAction(actionEvent -> {
            main.setCenter(speakerManager);
            main.setBottom(speakerManagerBottomMenu);
            MainView.getStage().setTitle(presenter.speakerManagerTitle());
            // Add all speakers to the list
            ObservableList<String> speakersList = FXCollections.observableArrayList();
            speakersList.addAll(presenter.getSpeakerNames());
            speakerManager.setItems(speakersList);
        });

            // Button that leads from speakerManagerBottomMenu to createSpeakerForm
            Button createSpeakerFormButton = new Button(presenter.createSpeakerFormButtonText());
            createSpeakerFormButton.setOnAction(actionEvent -> {
                main.setCenter(createSpeakerForm);
                main.setBottom(emptyBottomMenu);
                MainView.getStage().setTitle(presenter.createSpeakerFormTitle());
            });

                // Button that creates a speaker
                Button createSpeakerButton = new Button(presenter.createButtonText());
                createSpeakerButton.setOnAction(actionEvent -> {
                    // Remove previous messages
                    createSpeakerForm.getChildren().remove(createSpeakerSuccess);
                    createSpeakerForm.getChildren().remove(createSpeakerFailure);
                    // Validate and respond to input
                    if (filter.inputNewSpeakerUsername(createSpeakerNameInput.getText())) {
                        createSpeakerForm.add(createSpeakerSuccess, 0, 1, 2, 1);
                    } else {
                        createSpeakerForm.add(createSpeakerFailure, 0, 1, 2, 1);
                    }
                });

        // Button that leads from top menu to the Login Scene
        Button logoutButton = new Button(presenter.logoutButtonText());
        logoutButton.setOnAction(actionEvent -> MainView.setLoginScene());





        // TODO: Working section








        /*
         * Element inclusion
         */

        // Main Elements
        topMenu.getChildren().addAll(reManagerButton, speakerManagerButton, logoutButton);

        // reManager Elements
        reManagerBottomMenu.getChildren().addAll(createRoomFormButton, viewEventListButton);
            // createRoomForm Elements
            createRoomForm.add(roomCapacityPrompt, 0, 0);
            createRoomForm.add(roomCapacityInput, 1, 0);
            createRoomForm.add(createRoomButton, 2, 0);
            // viewEventList Elements
                viewEventListBottomMenu.getChildren().addAll(createEventButton);
                    // createEventForm Elements
                    createEventFormBottomMenu.getChildren().add(createNewEventButton);
                    createEventForm.add(createEventTitleLabel, 0, 0);
                    createEventForm.add(createEventTitleInput, 1, 0);
                    createEventForm.add(createEventSpeakerLabel, 0, 1);
                    createEventForm.add(createEventSpeakerInput, 1, 1);
                    createEventForm.add(createEventCapacityLabel, 0, 2);
                    createEventForm.add(createEventCapacityInput, 1, 2);
                    createEventForm.add(createEventDateLabel, 0, 3);
                    createEventForm.add(createEventDateInput, 1, 3);
                    createEventForm.add(createEventTimeLabel, 0, 4);
                    createEventForm.add(createEventTimeInput, 1, 4);

        // speakerManagerBottomMenu Elements
        speakerManagerBottomMenu.getChildren().addAll(createSpeakerFormButton);
            // createSpeakerForm Elements
            createSpeakerForm.add(createSpeakerNamePrompt, 0, 0);
            createSpeakerForm.add(createSpeakerNameInput, 1, 0);
            createSpeakerForm.add(createSpeakerButton, 2, 0);
    }
}
