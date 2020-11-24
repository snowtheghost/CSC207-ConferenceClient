package com.group0179.scenes;

import com.group0179.MainView;
import com.group0179.filters.AttendeeFilter;
import com.group0179.presenters.AttendeePresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * @author Template: Justin Chan
 */


public class AttendeeScene implements IScene {
    AttendeeFilter filter;
    AttendeePresenter presenter;

    Scene mainPanel;
    GridPane menu1 = new GridPane();
    GridPane menu2 = new GridPane();
    GridPane menu2sub1 = new GridPane();

    public AttendeeScene(AttendeeFilter filter, AttendeePresenter presenter) {
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

        // Elements for the Main Menu
        Button button1 = new Button("Command 1");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(menu1);
                MainView.getStage().setTitle("X Panel: Rooms and Events");
            }
        });

        Button button2 = new Button("Command 2");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(menu2);
                MainView.getStage().setTitle("X Panel: Speakers");
            }
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
        topMenu.getChildren().addAll(button1, button2, logoutButton);

        MainView.getStage().setScene(mainPanel);
        MainView.getStage().setTitle("X Panel: Main Menu");
    }
}
