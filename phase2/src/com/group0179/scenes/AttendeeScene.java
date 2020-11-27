package com.group0179.scenes;

import com.group0179.MainView;
import com.group0179.entities.Attendee;
import com.group0179.filters.AttendeeFilter;
import com.group0179.presenters.AttendeePresenter;
import com.group0179.presenters.Presenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.Group;

/**
 * @author Template: Justin Chan
 */


public class AttendeeScene implements IScene{
    private final AttendeeFilter filter;
    private final AttendeePresenter presenter;
    private final Text txtObj = new Text();
    private GridPane bottomMenu = new GridPane();

    public AttendeeScene(AttendeeFilter filter, AttendeePresenter presenter) {
        this.filter = filter;
        this.presenter = presenter;
    }

    public void setScene() {
        // Layout and scene for Full View
        Scene mainPanel = new Scene(main, x, y);

        // things buttons need to access
        AttendeePresenter presenter = this.presenter;
        Text txtObj = this.txtObj;
        txtObj.setWrappingWidth(x);


        // Attendee commands as buttons
        Button button1 = new Button("Display commands");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();
                bottomMenu.getChildren().add(txtObj);
                txtObj.setText(presenter.displayCommands());
            }
        });
        Button button2 = new Button("View Messages");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();


                // adds input command
                Text label = new Text("Enter username of who's messages you would like to see, enter 'all' for all.");
                int labelWidth = (x/3)*2;
                label.setWrappingWidth(labelWidth);
                GridPane.setConstraints(label, 0, 0);
                bottomMenu.getChildren().add(label);
                TextField textField = new TextField();
                GridPane.setConstraints(textField, 0, 1);
                bottomMenu.getChildren().add(textField);

                // adds submit button
                Button submitButton = new Button("Submit");
                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        // adds result when button pressed
                        Text result = new Text();
                        GridPane.setConstraints(result, 0, 4);
                        bottomMenu.getChildren().add(result);
                    }
                });
                GridPane.setConstraints(submitButton, 0, 3);
                bottomMenu.getChildren().add(submitButton);


            }
        });

        Button logoutButton = new Button("Log out");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainView.setLoginScene();
            }
        });

        // Set properties of the bottom menu
        bottomMenu.setAlignment(Pos.TOP_LEFT);
        bottomMenu.getChildren().add(this.txtObj);
        bottomMenu.setVgap(5);
        bottomMenu.setHgap(5);

        main.setTop(topMenu);
        main.setLeft(bottomMenu);

        // Add buttons to Top Menu
        topMenu.getChildren().addAll(button1, button2, logoutButton);
        MainView.getStage().setScene(mainPanel);
        MainView.getStage().setTitle("X Panel: Main Menu");
    }
}
