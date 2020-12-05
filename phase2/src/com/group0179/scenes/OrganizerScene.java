package com.group0179.scenes;

import com.group0179.MainView;
import com.group0179.PresenterFactory.OrganizerPresenterFactory;
import com.group0179.controllers.LoginController;
import com.group0179.filters.OrganizerFilter;
import com.group0179.presenters.IOrganizerPresenter;
import com.group0179.presenters.OrganizerPresenterEN;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * @author Justin Chan
 */

public class OrganizerScene implements IScene {
    OrganizerFilter filter;
    IOrganizerPresenter presenter;
    LoginController lc;
    OrganizerPresenterFactory factory;

    BorderPane main;
    HBox topMenu;
    HBox emptyBottomMenu;
    Scene mainScene;

    int currentRoomNumber = 0;
    int currentEventNumber = 0;

    public OrganizerScene(OrganizerFilter filter, OrganizerPresenterFactory factory, LoginController lc) {
        this.filter = filter;
        this.factory = factory;
        this.presenter = factory.getOrganizerPresenterEN();
        this.lc = lc;
    }

    public void setLanguageCH(){this.presenter = this.factory.getOrganizerPresenterCH();}

    public void constructScene() {
        /*
         * Main setup
         */

        main = new BorderPane();
        topMenu = new HBox();
        emptyBottomMenu = new HBox();
        mainScene = new Scene(main, x, y);
        topMenu.setSpacing(10); topMenu.setPadding(new Insets(10, 10, 10, 10));
        main.setTop(topMenu);


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
                    Label createEventDateLabel = new Label(presenter.eventDatePrompt());
                    TextField createEventDateInput = new TextField();
                    Label createEventTimeLabel = new Label(presenter.eventTimePrompt());
                    TextField createEventTimeInput = new TextField();
                    // Reschedule Event (given Room and Event)
                    GridPane rescheduleEventForm = new GridPane(); rescheduleEventForm.setVgap(10); rescheduleEventForm.setHgap(10); rescheduleEventForm.setPadding(new Insets(0, 10, 0, 10));
                    HBox rescheduleEventFormBottomMenu = new HBox(); rescheduleEventFormBottomMenu.setSpacing(10); rescheduleEventFormBottomMenu.setPadding(new Insets(10, 10, 10, 10));
                    Label rescheduleEventDateLabel = new Label(presenter.eventDatePrompt());
                    TextField rescheduleEventDateInput = new TextField();
                    Label rescheduleEventTimeLabel = new Label(presenter.eventTimePrompt());
                    TextField rescheduleEventTimeInput = new TextField();
                    Label rescheduleEventSuccess = new Label(presenter.rescheduleEventStatus(true));
                    Label rescheduleEventFailure = new Label(presenter.rescheduleEventStatus(false));

        // Speaker Manager
        ListView<String> speakerManager = new ListView<>();
        HBox speakerManagerBottomMenu = new HBox(); speakerManagerBottomMenu.setSpacing(10); speakerManagerBottomMenu.setPadding(new Insets(10, 10, 10, 10));
            // Create Speaker
            GridPane createSpeakerForm = new GridPane(); createSpeakerForm.setVgap(10); createSpeakerForm.setHgap(10); createSpeakerForm.setPadding(new Insets(0, 10, 0, 10));
            Label createSpeakerSuccess = new Label(presenter.createSpeakerStatus(true));
            Label createSpeakerFailure = new Label(presenter.createSpeakerStatus(false));
            Label createSpeakerNamePrompt = new Label(presenter.usernamePrompt());
            TextField createSpeakerNameInput = new TextField();

        // Message Menu
        HBox messageMenuBottomMenu = new HBox(); messageMenuBottomMenu.setSpacing(10); messageMenuBottomMenu.setPadding(new Insets(10, 10, 10, 10));
            // Message Custom Recipients
            GridPane messageCustomRecipientsForm = new GridPane(); messageCustomRecipientsForm.setVgap(10); messageCustomRecipientsForm.setHgap(10); messageCustomRecipientsForm.setPadding(new Insets(0, 10, 0, 10));
            HBox messageCustomRecipientsFormBottomMenu = new HBox(); messageCustomRecipientsFormBottomMenu.setSpacing(10); messageCustomRecipientsFormBottomMenu.setPadding(new Insets(10, 10, 10, 10));
            Label recipientsLabel = new Label(presenter.recipientsPrompt());
            TextField recipientsInput = new TextField();
            Label messageToRecipientsLabel = new Label(presenter.messagePrompt());
            TextArea messageToRecipientsInput = new TextArea();
            Label messageRecipientFailureDNE = new Label(presenter.messageRecipientExistence());
            Label messageRecipientFailureInvalid = new Label(presenter.messageRecipientValidity());
            Label messageRecipientSuccess = new Label(presenter.messageRecipientStatus());
            // Message All Attendees
            GridPane messageAttendeesForm = new GridPane(); messageAttendeesForm.setVgap(10); messageAttendeesForm.setHgap(10); messageAttendeesForm.setPadding(new Insets(0, 10, 0, 10));
            HBox messageAttendeesFormBottomMenu = new HBox(); messageAttendeesFormBottomMenu.setSpacing(10); messageAttendeesFormBottomMenu.setPadding(new Insets(10, 10, 10, 10));
            Label messageToAttendeesLabel = new Label(presenter.messagePrompt());
            TextArea messageToAttendeesInput = new TextArea();
            Label messageAttendeesSuccess = new Label(presenter.messageRecipientStatus());
            // Message All Speakers
            GridPane messageSpeakersForm = new GridPane(); messageSpeakersForm.setVgap(10); messageSpeakersForm.setHgap(10); messageSpeakersForm.setPadding(new Insets(0, 10, 0, 10));
            HBox messageSpeakersFormBottomMenu = new HBox(); messageSpeakersFormBottomMenu.setSpacing(10); messageSpeakersFormBottomMenu.setPadding(new Insets(10, 10, 10, 10));
            Label messageToSpeakersLabel = new Label(presenter.messagePrompt());
            TextArea messageToSpeakersInput = new TextArea();
            Label messageSpeakersSuccess = new Label(presenter.messageRecipientStatus());

        /*
         * Buttons and input process
         */

        // Button that leads from the Top Menu to the Room list (reManager)
        Button reManagerButton = new Button(presenter.reManagerButtonText());
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
                    currentRoomNumber = Integer.parseInt(room.split(" ")[1]) - 1;
                } catch (NullPointerException e) {
                    // "Stay" in current view
                    main.setCenter(reManager);
                    main.setBottom(reManagerBottomMenu);
                    MainView.getStage().setTitle(presenter.reManagerTitle());
                }
            });

                // Button that leads from viewEventList to createEventForm
                Button createEventFormButton = new Button(presenter.createEventFormButtonText());
                createEventFormButton.setOnAction(actionEvent -> {
                    main.setCenter(createEventForm);
                    main.setBottom(createEventFormBottomMenu);
                    MainView.getStage().setTitle(presenter.createEventFormTitle());
                });

                    // Button that creates an event if the inputs are correct
                    Button createEventButton = new Button(presenter.createButtonText());
                    createEventButton.setOnAction(actionEvent -> {
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
                        } else if (!filter.createEvent(createEventTitleInput.getText(), createEventSpeakerInput.getText(), createEventDateInput.getText(), createEventTimeInput.getText(), createEventCapacityInput.getText(), currentRoomNumber)) {
                            createEventForm.add(createEventDateTimeFailure, 0, 5, 2, 1);
                        } else {
                            createEventForm.add(createEventSuccess, 0, 5, 2, 1);
                        }
                    });

                Button removeEventButton = new Button(presenter.removeEventButtonText());
                removeEventButton.setOnAction(actionEvent -> {
                    try {
                        int eventNumber = viewEventList.getSelectionModel().getSelectedIndex();
                        filter.removeEvent(currentRoomNumber, eventNumber);
                        viewEventList.getItems().remove(eventNumber);
                    } catch (ArrayIndexOutOfBoundsException ignored) { }
                });

                // Button that leads from viewEventList to rescheduleEventForm
                Button rescheduleEventFormButton = new Button(presenter.rescheduleEventFormButtonText());
                rescheduleEventFormButton.setOnAction(actionEvent -> {
                    currentEventNumber = viewEventList.getSelectionModel().getSelectedIndex();
                    if (currentEventNumber != -1) {
                        main.setCenter(rescheduleEventForm);
                        main.setBottom(rescheduleEventFormBottomMenu);
                        MainView.getStage().setTitle(presenter.rescheduleEventFormTitle());
                    }
                });

                    // Button that reschedules event
                    Button rescheduleEventButton = new Button(presenter.rescheduleEventButtonText());
                    rescheduleEventButton.setOnAction(actionEvent -> {
                        rescheduleEventForm.getChildren().remove(rescheduleEventSuccess);
                        rescheduleEventForm.getChildren().remove(rescheduleEventFailure);
                        if (filter.rescheduleEvent(currentRoomNumber, currentEventNumber, rescheduleEventDateInput.getText(), rescheduleEventTimeInput.getText())) {
                            rescheduleEventForm.add(rescheduleEventSuccess, 0, 2, 2, 1);
                        } else {
                            rescheduleEventForm.add(rescheduleEventFailure, 0, 2, 2, 1);
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

        // Button that leads from top menu to the Message Menu
        Button messageMenuButton = new Button(presenter.messageMenuButtonText());
        messageMenuButton.setOnAction(actionEvent -> {
            main.setCenter(null);
            main.setBottom(messageMenuBottomMenu);
        });

            // Button that leads from messageMenu to Custom Recipients
            Button messageCustomRecipientsFormButton = new Button(presenter.messageCustomRecipientsFormButtonText());
            messageCustomRecipientsFormButton.setOnAction(actionEvent -> {
                main.setCenter(messageCustomRecipientsForm);
                main.setBottom(messageCustomRecipientsFormBottomMenu);
            });

                // Button that sends message
                Button sendToRecipientsButton = new Button(presenter.messageSendButtonText());
                sendToRecipientsButton.setOnAction(actionEvent -> {
                    messageCustomRecipientsForm.getChildren().remove(messageRecipientFailureDNE);
                    messageCustomRecipientsForm.getChildren().remove(messageRecipientFailureInvalid);
                    messageCustomRecipientsForm.getChildren().remove(messageRecipientSuccess);
                    if (!filter.recipientExists(recipientsInput.getText())) {
                        messageCustomRecipientsForm.add(messageRecipientFailureDNE, 0, 4);
                    } else if (!filter.recipientIsValid(recipientsInput.getText())) {
                        messageCustomRecipientsForm.add(messageRecipientFailureInvalid, 0, 4);
                    } else {
                        filter.sendRecipientMessage(recipientsInput.getText(), messageToRecipientsInput.getText());
                        messageCustomRecipientsForm.add(messageRecipientSuccess, 0, 4);
                    }
                });

            // Button that leads from messageMenuBottomMenu to Attendees
            Button messageAttendeesFormButton = new Button(presenter.messageAttendeesFormButtonText());
            messageAttendeesFormButton.setOnAction(actionEvent -> {
                main.setCenter(messageAttendeesForm);
                main.setBottom(messageAttendeesFormBottomMenu);
            });

                // Button that sends message
                Button sendToAttendeesButton = new Button(presenter.messageSendButtonText());
                sendToAttendeesButton.setOnAction(actionEvent -> {
                    messageAttendeesForm.getChildren().remove(messageAttendeesSuccess);
                    filter.sendAttendeesMessage(messageToAttendeesInput.getText());
                    messageAttendeesForm.add(messageAttendeesSuccess, 0, 2);
                });

            // Button that leads from messageMenuBottomMenu to Speakers
            Button messageSpeakersFormButton = new Button(presenter.messageSpeakersFormButtonText());
            messageSpeakersFormButton.setOnAction(actionEvent -> {
                main.setCenter(messageSpeakersForm);
                main.setBottom(messageSpeakersFormBottomMenu);
            });

                // Button that sends message
                Button sendToSpeakersButton = new Button(presenter.messageSendButtonText());
                sendToSpeakersButton.setOnAction(actionEvent -> {
                    messageSpeakersForm.getChildren().remove(messageSpeakersSuccess);
                    filter.sendSpeakersMessage(messageToSpeakersInput.getText());
                    messageSpeakersForm.add(messageSpeakersSuccess, 0, 2);
                });

        // Button that leads from top menu to the Login Scene
        Button logoutButton = new Button(presenter.logoutButtonText());
        logoutButton.setOnAction(actionEvent -> {
            lc.logoutUser();
            MainView.setLoginScene();
        });

        /*
         * Element inclusion
         */

        // topMenu Elements
        topMenu.getChildren().addAll(reManagerButton, speakerManagerButton, messageMenuButton, logoutButton);

            // reManager Elements
            reManagerBottomMenu.getChildren().addAll(createRoomFormButton, viewEventListButton);
                // createRoomForm Elements
                createRoomForm.add(roomCapacityPrompt, 0, 0);
                createRoomForm.add(roomCapacityInput, 1, 0);
                createRoomForm.add(createRoomButton, 2, 0);
                // viewEventList Elements
                viewEventListBottomMenu.getChildren().addAll(createEventFormButton, removeEventButton, rescheduleEventFormButton);
                    // createEventForm Elements
                    createEventFormBottomMenu.getChildren().add(createEventButton);
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
                    // rescheduleEventForm Elements
                    rescheduleEventFormBottomMenu.getChildren().add(rescheduleEventButton);
                    rescheduleEventForm.add(rescheduleEventDateLabel, 0, 0);
                    rescheduleEventForm.add(rescheduleEventDateInput, 1, 0);
                    rescheduleEventForm.add(rescheduleEventTimeLabel, 0, 1);
                    rescheduleEventForm.add(rescheduleEventTimeInput, 1, 1);

            // speakerManagerBottomMenu Elements
            speakerManagerBottomMenu.getChildren().addAll(createSpeakerFormButton);
                // createSpeakerForm Elements
                createSpeakerForm.add(createSpeakerNamePrompt, 0, 0);
                createSpeakerForm.add(createSpeakerNameInput, 1, 0);
                createSpeakerForm.add(createSpeakerButton, 2, 0);

            // messageMenuBottomMenu Elements
            messageMenuBottomMenu.getChildren().addAll(messageCustomRecipientsFormButton, messageAttendeesFormButton, messageSpeakersFormButton);
                // messageCustomRecipientsMenu Elements
                messageCustomRecipientsForm.add(recipientsLabel, 0, 0);
                messageCustomRecipientsForm.add(recipientsInput, 0, 1);
                messageCustomRecipientsForm.add(messageToRecipientsLabel, 0, 2);
                messageCustomRecipientsForm.add(messageToRecipientsInput, 0, 3);
                messageCustomRecipientsFormBottomMenu.getChildren().add(sendToRecipientsButton);
                // messageAttendeesMenu Elements
                messageAttendeesForm.add(messageToAttendeesLabel, 0, 0);
                messageAttendeesForm.add(messageToAttendeesInput, 0, 1);
                messageAttendeesFormBottomMenu.getChildren().add(sendToAttendeesButton);
                // messageSpeakersMenu Elements
                messageSpeakersForm.add(messageToSpeakersLabel, 0, 0);
                messageSpeakersForm.add(messageToSpeakersInput, 0, 1);
                messageSpeakersFormBottomMenu.getChildren().add(sendToSpeakersButton);
    }

    /**
     * This method creates and manipulates all GUI related functions under the Organizer's responsibilities.
     * Further documentation of the structure below
     */
    public void setScene() {
        MainView.getStage().setScene(mainScene);
        MainView.getStage().setTitle(presenter.mainSceneTitle());
    }
}
