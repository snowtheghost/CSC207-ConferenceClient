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
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.Group;

/**
 * @author Template: Justin Chan
 */


public class AttendeeScene implements IScene{
    private final AttendeeFilter filter;
    private final AttendeePresenter presenter;
    private final Text txtObj = new Text();
    private final GridPane bottomMenu = new GridPane();
    private final FlowPane topMenu = new FlowPane();
    BorderPane main = new BorderPane();

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

        Button button1 = new Button("Send message");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();

                // adds target user and msg content label
                Text label1 = new Text("Enter username of who you would like to message.");
                Text label2 = new Text("Enter message content");
                int labelWidth = (x/3)*2;
                label1.setWrappingWidth(labelWidth);
                label2.setWrappingWidth(labelWidth);

                // adds the input boxes for those
                TextField textField1 = new TextField();
                TextField textField2 = new TextField();

                // adds everything to grid plane
                GridPane.setConstraints(label1, 0, 1);
                bottomMenu.getChildren().add(label1);
                GridPane.setConstraints(textField1, 0, 2);
                bottomMenu.getChildren().add(textField1);

                GridPane.setConstraints(label2, 0, 3);
                bottomMenu.getChildren().add(label2);
                GridPane.setConstraints(textField2, 0, 4);
                bottomMenu.getChildren().add(textField2);

                // adds send button
                Button submitButton = new Button("Send");
                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Text result = new Text(presenter.message(textField1.getText(), textField2.getText()));
                        GridPane.setConstraints(result, 0, 6);
                        bottomMenu.getChildren().add(result);
                    }
                });
                GridPane.setConstraints(submitButton, 0, 5);
                bottomMenu.getChildren().add(submitButton);
            }
        });


        Button button2 = new Button("View messages");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();


                // adds input box
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
                        Text result = new Text(presenter.viewMessages(textField.getText()));
                        GridPane.setConstraints(result, 0, 4);
                        bottomMenu.getChildren().add(result);
                    }
                });
                GridPane.setConstraints(submitButton, 0, 3);
                bottomMenu.getChildren().add(submitButton);


            }
        });

        Button button3 = new Button("View all events");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();
                bottomMenu.getChildren().add(txtObj);
                txtObj.setText(presenter.viewAllEvents());
            }
        });

        Button button4 = new Button("View signed up events");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();
                bottomMenu.getChildren().add(txtObj);
                txtObj.setText(presenter.viewSignedUpEvents());
            }
        });




        Button logoutButton = new Button("Log out");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainView.setLoginScene();
            }
        });

        // Add buttons to Top Menu and sets properties
        topMenu.getChildren().addAll(button1, button2, button3, logoutButton);


        // Set properties of the bottom menu
        bottomMenu.setAlignment(Pos.TOP_LEFT);
        bottomMenu.getChildren().add(this.txtObj);
        bottomMenu.setVgap(5);
        bottomMenu.setHgap(5);

        main.setTop(topMenu);
        main.setLeft(bottomMenu);

        //Sets main scene as current scene.
        MainView.getStage().setScene(mainPanel);
        MainView.getStage().setTitle("Attendee Panel");
    }
}
