package com.group0179.scenes;

import com.group0179.MainView;
import com.group0179.entities.Attendee;
import com.group0179.filters.AttendeeFilter;
import com.group0179.presenters.*;
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
    private final AttendeePresenter presenter;
    private IAttendeePresenter langPresenter = new AttendeePresenterEN();
    private Text txtObj;
    private GridPane bottomMenu;
    private FlowPane topMenu;
    private Scene mainPanel;
    private BorderPane main;

    /**
     * The view responsible for what an attendee see's when they login.
     * @param filter I dont think I need this
     * @param presenter Takes user inputs and talks to the backend with it.
     */
    public AttendeeScene(AttendeeFilter filter, AttendeePresenter presenter) {
        this.presenter = presenter;
    }

    public void changePresenter(String languageType){
        if (languageType.equals("Chinese")){
            this.langPresenter = new AttendeePresenterCH();
            this.presenter.changeLangPresenter(this.langPresenter);
        }
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

    @Override
    public void constructScene() {
        txtObj = new Text();
        bottomMenu = new GridPane();
        topMenu = new FlowPane();
        main = new BorderPane();
        // Layout and scene for Full View
        mainPanel = new Scene(main, x, y);

        // things buttons need to access
        AttendeeScene atScene = this;
        AttendeePresenter presenter = this.presenter;
        Text txtObj = this.txtObj;
        txtObj.setWrappingWidth(x);

        Button button1 = new Button(langPresenter.sendMessage());
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();

                // Creates labels and input space for form
                Text label1 = atScene.txtObjCreater(langPresenter.enterUsername(), x/1.6);
                Text label2 = atScene.txtObjCreater(langPresenter.enterMsgContent(), x/1.5);
                TextField textField1 = new TextField();
                TextField textField2 = new TextField();

                // adds those to pane so it can be displayed
                displayForm(label1, textField1, bottomMenu, 0);
                displayForm(label2, textField2, bottomMenu, 2);

                // adds send button and result message to pane
                Button submitButton = new Button(langPresenter.send());
                Text result = atScene.txtObjCreater("", x/1.5);
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


        Button button2 = new Button(langPresenter.viewMessages());
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();


                // adds input box
                Text label = atScene.txtObjCreater(langPresenter.usersToSee(), x/1.5);
                TextField textField = new TextField();
                displayForm(label, textField, bottomMenu, 0);

                // adds submit button and result
                Text result = atScene.txtObjCreater("", x/1.5);
                Button submitButton = new Button(langPresenter.submit());
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

        Button button3 = new Button(langPresenter.viewAllEvents());
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();
                bottomMenu.getChildren().add(txtObj);
                txtObj.setText(presenter.viewAllEvents());
            }
        });

        Button button4 = new Button(langPresenter.viewAllSignedUpevents());
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();
                bottomMenu.getChildren().add(txtObj);
                txtObj.setText(presenter.viewSignedUpEvents());
            }
        });

        Button button5 = new Button(langPresenter.joinLeaveButtonText());
        button5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();
                // adds input prompts
                Text label0 = atScene.txtObjCreater(langPresenter.joinOrLeaveLabel(), x/1.4);
                Text label1 = atScene.txtObjCreater(langPresenter.enterRoomNumberLabel(),x/1.5);
                Text label2 = atScene.txtObjCreater(langPresenter.enterEventNameLabel(),x/1.5);

                // adds the input boxes for those to pane
                TextField textField0 = new TextField();
                TextField textField1 = new TextField();
                TextField textField2 = new TextField();

                // adds everything to grid plane

                displayForm(label0, textField0, bottomMenu, 0);
                displayForm(label1, textField1, bottomMenu, 2);
                displayForm(label2, textField2, bottomMenu, 4);

                // adds send button and result label to pane
                Button submitButton = new Button(langPresenter.attemptJoinButton());
                Text result = atScene.txtObjCreater("", x/1.5);
                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        result.setText(presenter.joinLeaveEvent(textField0.getText(), textField1.getText(), textField2.getText()));
                    }
                });
                GridPane.setConstraints(submitButton, 0, 6);
                bottomMenu.getChildren().add(submitButton);
                GridPane.setConstraints(result, 0, 7);
                bottomMenu.getChildren().add(result);
            }
        });

        Button logoutButton = new Button(langPresenter.logoutButton());
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
    }

    /**
     * Sets the screen to attendee view.
     */
    @Override
    public void setScene() {
        //Sets main scene as current scene.
        MainView.getStage().setScene(mainPanel);
        MainView.getStage().setTitle("Attendee Panel");
    }
}
