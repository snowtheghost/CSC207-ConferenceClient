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

    /**
     * The view responsible for what an attendee see's when they login.
     * @param filter I dont think I need this
     * @param presenter Takes user inputs and talks to the backend with it.
     */
    public AttendeeScene(AttendeeFilter filter, AttendeePresenter presenter) {
        this.filter = filter;
        this.presenter = presenter;
    }

    /**
     * Creates a Text object with desired length
     * @param text The content on the Text object
     * @param objectLength The desired length of the object.
     * @return The Text object.
     */
    private Text txtObjCreater(String text, double objectLength){
        Text label1 = new Text(text);
        label1.setWrappingWidth(objectLength);
        return label1;
    }

    /**
     * Adds label and input space to displaying pane.
     * @param label What to put in the input space
     * @param textField input space
     * @param gridPane the displaying pane
     * @param gridIndex the position of the pane to be inserted into.
     */
    private void displayForm(Text label, TextField textField, GridPane gridPane, int gridIndex){
        GridPane.setConstraints(label, 0, gridIndex);
        gridPane.getChildren().add(label);
        GridPane.setConstraints(textField, 0, gridIndex+1);
        gridPane.getChildren().add(textField);

    }


    /**
     * Sets the screen to attendee view.
     */
    @Override
    public void setScene() {
        // Layout and scene for Full View
        Scene mainPanel = new Scene(main, x, y);

        // things buttons need to access
        AttendeeScene atScene = this;
        AttendeePresenter presenter = this.presenter;
        Text txtObj = this.txtObj;
        txtObj.setWrappingWidth(x);

        Button button1 = new Button("Send message");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();

                // Creates labels and input space for form
                Text label1 = atScene.txtObjCreater("Enter username of who you would like to message.", x/1.6);
                Text label2 = atScene.txtObjCreater("Enter message content", x/1.5);
                TextField textField1 = new TextField();
                TextField textField2 = new TextField();

                // adds those to pane so it can be displayed
                displayForm(label1, textField1, bottomMenu, 0);
                displayForm(label2, textField2, bottomMenu, 2);

                // adds send button and result message to pane
                Button submitButton = new Button("Send");
                Text result = new Text(presenter.message(textField1.getText(), textField2.getText()));
                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        result.setText(presenter.message(textField1.getText(), textField2.getText()));
                    }
                });
                GridPane.setConstraints(submitButton, 0, 5);
                bottomMenu.getChildren().add(submitButton);
                GridPane.setConstraints(result, 0, 6);
                bottomMenu.getChildren().add(result);
            }
        });


        Button button2 = new Button("View messages");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();


                // adds input box
                Text label = atScene.txtObjCreater("Enter username of who's messages you would like to see, enter 'all' for all.", x/1.5);
                TextField textField = new TextField();
                displayForm(label, textField, bottomMenu, 0);

                // adds submit button and result
                Text result = new Text(presenter.viewMessages(textField.getText()));
                Button submitButton = new Button("Submit");
                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        // adds result when button pressed
                        result.setText(presenter.viewMessages(textField.getText()));
                    }
                });
                GridPane.setConstraints(submitButton, 0, 3);
                bottomMenu.getChildren().add(submitButton);
                GridPane.setConstraints(result, 0, 4);
                bottomMenu.getChildren().add(result);


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

        Button button5 = new Button("Join/Leave Event");
        button5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();
                // adds input prompts
                Text label0 = atScene.txtObjCreater("Would you like to join or leave the event? \n " +
                        "Enter 'joining' to join or 'leaving' to leave.", x/1.5);
                Text label1 = atScene.txtObjCreater("Enter room number of event:",x/1.5);
                Text label2 = atScene.txtObjCreater("Enter name of event:",x/1.5);

                // adds the input boxes for those to pane
                TextField textField0 = new TextField();
                TextField textField1 = new TextField();
                TextField textField2 = new TextField();

                // adds everything to grid plane

                displayForm(label0, textField0, bottomMenu, 0);
                displayForm(label1, textField1, bottomMenu, 2);
                displayForm(label2, textField2, bottomMenu, 4);

                // adds send button and result label to pane
                Button submitButton = new Button("Attempt join");
                Text result = atScene.txtObjCreater("", x/1.5);
                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        result.setText(presenter.joinLeaveEvent(textField0.getText(), textField1.getText(), textField2.getText()));
                    }
                });
                GridPane.setConstraints(submitButton, 0, 5);
                bottomMenu.getChildren().add(submitButton);
                GridPane.setConstraints(result, 0, 6);
                bottomMenu.getChildren().add(result);
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
        topMenu.getChildren().addAll(button1, button2, button3, button4, button5, logoutButton);

        // Set properties of the bottom menu
        bottomMenu.setAlignment(Pos.TOP_LEFT);
        bottomMenu.getChildren().add(this.txtObj);
        int maxWidth = (x/3)*2;
        bottomMenu.setMaxWidth(maxWidth);
        bottomMenu.setVgap(5);
        bottomMenu.setHgap(5);

        main.setTop(topMenu);
        main.setLeft(bottomMenu);

        //Sets main scene as current scene.
        MainView.getStage().setScene(mainPanel);
        MainView.getStage().setTitle("Attendee Panel");
    }
}
