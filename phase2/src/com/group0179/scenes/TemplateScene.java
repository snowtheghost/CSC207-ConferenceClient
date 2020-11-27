package com.group0179.scenes;

import com.group0179.MainView;
import com.group0179.filters.Filter;
import com.group0179.presenters.Presenter;
import javafx.application.Platform;
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


public class TemplateScene implements IScene {
    Filter filter;
    Presenter presenter;

    Scene mainPanel;
    BorderPane main = new BorderPane();
    HBox topMenu = new HBox();
    HBox emptyBottomMenu = new HBox();
    GridPane menu1 = new GridPane();
    GridPane menu2 = new GridPane();
    GridPane menu2sub1 = new GridPane();

    public TemplateScene(Filter filter, Presenter presenter) {
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
