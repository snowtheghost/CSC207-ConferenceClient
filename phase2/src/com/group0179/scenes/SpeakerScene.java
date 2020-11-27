package com.group0179.scenes;

import com.group0179.MainView;
import com.group0179.filters.SpeakerFilter;
import com.group0179.presenters.SpeakerPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * @author Template: Justin Chan
 */

public class SpeakerScene implements IScene {
    SpeakerFilter filter;
    SpeakerPresenter presenter;

    Scene mainPanel;
    GridPane menu1 = new GridPane();
    GridPane menu2 = new GridPane();
    GridPane menu2sub1 = new GridPane();
    BorderPane main = new BorderPane();
    HBox topMenu = new HBox();
    HBox emptyBottomMenu = new HBox();


    public SpeakerScene(SpeakerFilter filter, SpeakerPresenter presenter) {
        this.filter = filter;
        this.presenter = presenter;
    }

    public void setScene() {
        // Layout and scene for Full View
        mainPanel = new Scene(main, x, y);

        // Layout and scene for Menu
        topMenu.setSpacing(10);

        menu2.setVgap(2.5);
        menu2.setHgap(2.5);
        menu2sub1.setVgap(2.5);
        menu2sub1.setHgap(2.5);
        ListView<String> viewEventList = new ListView<>();
        ListView<String> viewSpeakingEventList = new ListView<>();

        // Elements for the Main Menu
        Button viewEventsButton = new Button(this.presenter.viewAllEventsButton());
        viewEventsButton.setOnAction(actionEvent -> {
            main.setCenter(viewEventList);
            MainView.getStage().setTitle(this.presenter.viewAllEventsButton());
            // Get and add all events to the list
            ObservableList<String> eventsList = FXCollections.observableArrayList();
            eventsList.addAll(presenter.getEventListArray());
            viewEventList.setItems(eventsList);
        });

        Button viewSpeakingEventsButton = new Button(this.presenter.viewSpeakingEventsButton());
        viewSpeakingEventsButton.setOnAction(actionEvent -> {
            main.setCenter(viewSpeakingEventList);
            MainView.getStage().setTitle(this.presenter.viewSpeakingEventsButton());
            // Get and add all events to the list
            ObservableList<String> eventsList = FXCollections.observableArrayList();
            eventsList.addAll(presenter.getSpeakingEventListArray());
            viewSpeakingEventList.setItems(eventsList);
        });


        Button logoutButton = new Button("Log out");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainView.setLoginScene();
            }
        });

        // Set initial scene for Main Panel
        main.setTop(topMenu);

        // Add buttons to Top Menu
        topMenu.getChildren().addAll(viewEventsButton, viewSpeakingEventsButton, logoutButton);

        MainView.getStage().setScene(mainPanel);
        MainView.getStage().setTitle("X Panel: Main Menu");
    }
}
