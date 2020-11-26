package com.group0179.scenes;

import com.group0179.MainView;
import com.group0179.entities.Attendee;
import com.group0179.filters.AttendeeFilter;
import com.group0179.presenters.AttendeePresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.Group;

/**
 * @author Template: Justin Chan
 */


public class AttendeeScene implements IScene {
    private final AttendeeFilter filter;
    private final AttendeePresenter presenter;
    private Group displayedText = new Group();

    Scene mainPanel;
    GridPane menu1 = new GridPane();

    public AttendeeScene(AttendeeFilter filter, AttendeePresenter presenter) {
        this.filter = filter;
        this.presenter = presenter;
    }

    public void updateText(String text){
        Text txtObj = new Text(text);
        txtObj.setX(30);
        txtObj.setY(30);
        this.displayedText = new Group(txtObj);
        topMenu.getChildren().addAll(this.displayedText);
        MainView.getStage().setScene(mainPanel);
    }

    public void setScene() {
        // Layout and scene for Full View
        mainPanel = new Scene(main, x, y);

        // things buttons need to access
        AttendeeScene view = this;
        // Attendee commands as buttons
        Button button1 = new Button("View Messages");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("pressed");
                view.updateText("haaaaaaaa");
                //Text allMessages = new Text(presenter.runInput("view messages"));
            }
        });

        Button button2 = new Button("Command 2");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(menu1);
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
        topMenu.getChildren().addAll(button1, button2, logoutButton, this.displayedText);

        MainView.getStage().setScene(mainPanel);
        MainView.getStage().setTitle("X Panel: Main Menu");
    }
}
