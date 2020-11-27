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

        // Room Event Scenes
        GridPane reMenu = new GridPane(); reMenu.setHgap(10); reMenu.setVgap(10); reMenu.setPadding(new Insets(10, 10, 10, 10));
            // Create Room
            GridPane createRoomForm = new GridPane(); createRoomForm.setVgap(10); createRoomForm.setHgap(10); createRoomForm.setPadding(new Insets(10, 10, 10, 10));
                    Label createRoomSuccess = new Label(presenter.createRoomStatus(true));
                    Label createRoomFailure = new Label(presenter.createRoomStatus(false));
                    Label roomCapacityPrompt = new Label(presenter.roomCapacityPrompt());
                    TextField roomCapacityInput = new TextField();
            // View Rooms
            ListView<String> viewRoomList = new ListView<>();
            HBox viewRoomListBottomMenu = new HBox(); viewRoomListBottomMenu.setSpacing(10); viewRoomListBottomMenu.setPadding(new Insets(10, 10, 10, 10));
                // View Events (given Room)
                ListView<String> viewEventList = new ListView<>();
                HBox viewEventListBottomMenu = new HBox(); viewEventListBottomMenu.setSpacing(10); viewEventListBottomMenu.setPadding(new Insets(10, 10, 10, 10));
                    // Create Event (given Room)
                    GridPane createEventForm = new GridPane(); createEventForm.setVgap(10); createEventForm.setHgap(10); createEventForm.setPadding(new Insets(10, 10, 10, 10));
                    HBox createEventBottomMenu = new HBox(); createEventBottomMenu.setSpacing(10); createEventBottomMenu.setPadding(new Insets(10, 10, 10, 10));

        // Speaker Management Scenes
        GridPane speakerManagementMenu = new GridPane(); speakerManagementMenu.setVgap(10); speakerManagementMenu.setHgap(10); speakerManagementMenu.setPadding(new Insets(10, 10, 10, 10));
            // View Speakers
            ListView<String> viewSpeakerList = new ListView<>();
            HBox viewSpeakerListBottomMenu = new HBox(); viewSpeakerListBottomMenu.setSpacing(10); viewSpeakerListBottomMenu.setPadding(new Insets(10, 10, 10, 10));
                // Create Speaker
                GridPane createSpeakerForm = new GridPane(); createSpeakerForm.setVgap(10); createSpeakerForm.setHgap(10); createSpeakerForm.setPadding(new Insets(10, 10, 10, 10));
                Label createSpeakerSuccess = new Label(presenter.createSpeakerStatus(true));
                Label createSpeakerFailure = new Label(presenter.createSpeakerStatus(false));
                Label createSpeakerNamePrompt = new Label(presenter.usernamePrompt());
                TextField createSpeakerNameInput = new TextField();

        /*
         * Buttons and input process
         */

        // Button that leads from the top menu to the Rooms and Events Menu (reMenu)
        Button reMenuButton = new Button(presenter.reMenuButtonText());
        reMenuButton.setOnAction(actionEvent -> {
            main.setCenter(reMenu);
            main.setBottom(emptyBottomMenu);
            MainView.getStage().setTitle(presenter.reMenuTitle());
        });

            // Button that leads from reMenu to the Room creation form (createRoomForm)
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

            // Button that leads from reMenu to the Room list (viewRoomList)
            Button viewRoomListButton = new Button(presenter.viewRoomListButtonText());
            viewRoomListButton.setOnAction(actionEvent -> {
                main.setCenter(viewRoomList);
                main.setBottom(viewRoomListBottomMenu);
                MainView.getStage().setTitle(presenter.viewRoomListTitle());
                // Get and add all rooms to the list
                ObservableList<String> roomsList = FXCollections.observableArrayList();
                roomsList.addAll(presenter.getRoomListArray());
                viewRoomList.setItems(roomsList);
            });

                // Button that leads from viewRoomList to viewEventList
                Button viewEventListButton = new Button(presenter.viewEventListButtonText());
                viewEventListButton.setOnAction(actionEvent -> {
                    main.setCenter(viewEventList);
                    main.setBottom(viewEventListBottomMenu);
                    MainView.getStage().setTitle(presenter.viewEventListTitle());
                    // Try to form a list of events for the selected room
                    try {
                        // Get the currently selected room in the list
                        String room = viewRoomList.getSelectionModel().getSelectedItem();
                        // Add all events in the room to the eventList
                        ObservableList<String> eventList = FXCollections.observableArrayList();
                        eventList.addAll(presenter.getEvents(room));
                        viewEventList.setItems(eventList);
                        // Set the currentRoomNumber
                        currentRoomNumber = presenter.getRoomNumber(room);
                    } catch (NullPointerException e) {
                        // "Stay" in current view
                        main.setCenter(viewRoomList);
                        main.setBottom(viewRoomListBottomMenu);
                        MainView.getStage().setTitle(presenter.viewRoomListTitle());
                    }
                });

        // Button that leads from top menu to the Speaker Management Menu (speakerManagementMenu)
        Button speakerManagementButton = new Button(presenter.speakerManagementButtonText());
        speakerManagementButton.setOnAction(actionEvent -> {
            main.setCenter(speakerManagementMenu);
            main.setBottom(emptyBottomMenu);
            MainView.getStage().setTitle(presenter.speakerManagementMenuTitle());
        });

            // Button that leads from speakerManagementMenu to createSpeakerForm
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

            // Button that leads from speakerManagerMenu to viewSpeakerList
            Button speakerListButton = new Button(presenter.speakerListButtonText());
            speakerListButton.setOnAction(actionEvent -> {
                main.setCenter(viewSpeakerList);
                main.setBottom(viewSpeakerListBottomMenu);
                MainView.getStage().setTitle(presenter.speakerListTitle());
                // Add all speakers to the list
                ObservableList<String> speakersList = FXCollections.observableArrayList();
                speakersList.addAll(presenter.getSpeakerNames());
                viewSpeakerList.setItems(speakersList);
            });

        // Button that leads from top menu to the Login Scene
        Button logoutButton = new Button(presenter.logoutButtonText());
        logoutButton.setOnAction(actionEvent -> MainView.setLoginScene());





        // TODO: Working section
        Button createNewEventButton = new Button(presenter.createButtonText());

        Label createEventTitleLabel = new Label(presenter.eventTitlePrompt());
        TextField createEventTitleInput = new TextField();

        Button createEventButton = new Button(presenter.createEventButtonText());
        createEventButton.setOnAction(actionEvent -> {
            main.setCenter(createEventForm);
            main.setBottom(createEventBottomMenu);
            MainView.getStage().setTitle(presenter.createEventSceneTitle());

            createEventForm.add(createEventTitleLabel, 0, 0);
            createEventForm.add(createEventTitleInput, 1, 0);
        });






        /*
         * Element inclusion
         */

        // Main Elements
        topMenu.getChildren().addAll(reMenuButton, speakerManagementButton, logoutButton);

            // reMenu Elements
            reMenu.add(createRoomFormButton, 0, 0);
            reMenu.add(viewRoomListButton, 1, 0);
                // createRoomForm Elements
                createRoomForm.add(roomCapacityPrompt, 0, 0);
                createRoomForm.add(roomCapacityInput, 1, 0);
                createRoomForm.add(createRoomButton, 2, 0);
                // viewRoomList Elements
                viewRoomListBottomMenu.getChildren().addAll(viewEventListButton);
                    // viewEventList Elements
                    viewEventListBottomMenu.getChildren().addAll(createEventButton);
                        // createEventForm Elements
                        createEventBottomMenu.getChildren().add(createNewEventButton);

            // speakerManagementMenu Elements
            speakerManagementMenu.add(speakerListButton, 0, 0);
                // viewSpeakerList Elements
                viewSpeakerListBottomMenu.getChildren().addAll(createSpeakerFormButton);
                    // createSpeakerForm Elements
                    createSpeakerForm.add(createSpeakerNamePrompt, 0, 0);
                    createSpeakerForm.add(createSpeakerNameInput, 1, 0);
                    createSpeakerForm.add(createSpeakerButton, 2, 0);

    }
}
