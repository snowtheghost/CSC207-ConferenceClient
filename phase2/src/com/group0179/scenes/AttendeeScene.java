package com.group0179.scenes;

import com.group0179.MainView;
import com.group0179.PresenterFactory.AttendeePresenterFactory;
import com.group0179.controllers.AttendeePresenter;
import com.group0179.controllers.AutofillController;
import com.group0179.controllers.LoginController;
import com.group0179.presenters.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import sun.awt.SunHints;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AttendeeScene implements IScene{
    private final AttendeePresenter presenter;
    private final LoginController lc;
    private final AttendeePresenterFactory factory;
    private final AutofillController autofill;
    private IAttendeePresenter langPresenter;
    private GridPane bottomMenu;
    private Scene mainPanel;

    /**
     * The view responsible for what an attendee see's when they login.
     * @param presenter Takes user inputs and talks to the backend with it.
     * @param autofill
     */
    public AttendeeScene(AttendeePresenter presenter, LoginController lc, AttendeePresenterFactory factory, AutofillController autofill) {
        this.presenter = presenter;
        this.factory = factory;
        this.langPresenter = factory.getAttendeePresenterEN();
        this.lc = lc;
        this.autofill = autofill;
    }

    public void setLanguage(String languageType){
        if (languageType.equals("Chinese")){
            this.langPresenter = factory.getAttendeePresenterCH();
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
        Text txtObj = new Text();
        bottomMenu = new GridPane();
        FlowPane topMenu = new FlowPane();
        BorderPane main = new BorderPane();
        // Layout and scene for Full View
        mainPanel = new Scene(main, x, y);

        // things buttons need to access
        AttendeeScene atScene = this;
        AttendeePresenter presenter = this.presenter;
        txtObj.setWrappingWidth(x);

        Button button1 = new Button(langPresenter.sendMessage());
        button1.setOnAction(actionEvent -> {
            bottomMenu.getChildren().clear();

            // Creates labels and input space for form
            Text label1 = atScene.txtObjCreater(langPresenter.enterUsername(), x/1.6);
            Text label2 = atScene.txtObjCreater(langPresenter.enterMsgContent(), x/1.5);
            TextField textField1 = new TextField();
            TextField textField2 = new TextField();

            Text result = atScene.txtObjCreater("", x/1.5);

            //autocomplete baby lets gooooo
            AtomicReference<String> input1 = new AtomicReference<>("");
            textField1.setOnKeyPressed(event -> {
                String codeString = event.getCode().toString();
                if (codeString.length() < 2  | codeString == "BACK_SPACE"){
                    if (codeString == "BACK_SPACE" & input1.toString() != ""){
                        input1.set(input1.toString().substring(0, input1.toString().length() - 1));
                    }
                    else {
                        input1.set(input1 + codeString);
                    }
                }

                List<String> auto = autofill.autofillUsername(input1);
                result.setText(auto.toString());
            });

            // adds those to pane so it can be displayed
            displayForm(label1, textField1, bottomMenu, 0);
            displayForm(label2, textField2, bottomMenu, 2);

            // adds send button and result message to pane
            Button submitButton = new Button(langPresenter.send());

            submitButton.setOnAction(actionEvent1 ->
                    result.setText(presenter.message(textField1.getText(), textField2.getText())));
            GridPane.setConstraints(submitButton, 0, 5);
            bottomMenu.getChildren().add(submitButton);
            GridPane.setConstraints(result, 0, 6);
            bottomMenu.getChildren().add(result);
        });


        Button button2 = new Button(langPresenter.viewMessages());
        button2.setOnAction(actionEvent -> {
            bottomMenu.getChildren().clear();


            // adds input box
            Text label = atScene.txtObjCreater(langPresenter.usersToSee(), x/1.5);
            TextField textField = new TextField();
            displayForm(label, textField, bottomMenu, 0);



            // adds submit button and result
            Text result = atScene.txtObjCreater("", x/1.5);

            AtomicReference<String> input1 = new AtomicReference<>("");
            textField.setOnKeyPressed(event -> {
                String codeString = event.getCode().toString();
                if (codeString.length() < 2  | codeString == "BACK_SPACE"){
                    if (codeString == "BACK_SPACE" & input1.toString() != ""){
                        input1.set(input1.toString().substring(0, input1.toString().length() - 1));
                    }
                    else {
                        input1.set(input1 + codeString);
                    }
                }

                List<String> auto = autofill.autofillUsername(input1);
                result.setText(auto.toString());
            });

            Button submitButton = new Button(langPresenter.submit());
            submitButton.setOnAction(actionEvent13 -> {
                // adds result when button pressed
                result.setText(presenter.viewMessages(textField.getText()));
            });
            GridPane.setConstraints(submitButton, 0, 3);
            bottomMenu.getChildren().add(submitButton);
            GridPane.setConstraints(result, 0, 4);
            bottomMenu.getChildren().add(result);


        });

        Button button3 = new Button(langPresenter.viewAllEvents());
        button3.setOnAction(actionEvent -> {
            bottomMenu.getChildren().clear();
            bottomMenu.getChildren().add(txtObj);
            txtObj.setText(presenter.viewAllEvents());
        });

        Button button4 = new Button(langPresenter.viewAllSignedUpevents());
        button4.setOnAction(actionEvent -> {
            bottomMenu.getChildren().clear();
            bottomMenu.getChildren().add(txtObj);
            txtObj.setText(presenter.viewSignedUpEvents());
        });

        Button button5 = new Button(langPresenter.joinLeaveButtonText());
        button5.setOnAction(actionEvent -> {
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

            AtomicReference<String> input1 = new AtomicReference<>("");
            textField2.setOnKeyPressed(event -> {
                String codeString = event.getCode().toString();
                if (codeString.length() < 2  | codeString == "BACK_SPACE"){
                    if (codeString == "BACK_SPACE" & input1.toString() != ""){
                        input1.set(input1.toString().substring(0, input1.toString().length() - 1));
                    }
                    else {
                        input1.set(input1 + codeString);
                    }
                }

                List<String> auto = autofill.autofillEvents(input1);
                result.setText(auto.toString());
            });


            submitButton.setOnAction(actionEvent12 ->
                    result.setText(presenter.joinLeaveEvent(textField0.getText(), textField1.getText(), textField2.getText())));
            GridPane.setConstraints(submitButton, 0, 6);
            bottomMenu.getChildren().add(submitButton);
            GridPane.setConstraints(result, 0, 7);
            bottomMenu.getChildren().add(result);
        });

        Button button6 = new Button(langPresenter.userStats());
        button6.setOnAction(actionEvent -> {
            bottomMenu.getChildren().clear();
            // displays user stats info
            Text label0 = atScene.txtObjCreater(presenter.getUserStats(), x/1.5);
            GridPane.setConstraints(label0, 0, 0);
            bottomMenu.getChildren().add(label0);
            // displays average login chart
            NumberAxis xAxis = new NumberAxis();
            NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel(langPresenter.howManyLoginsAgo());
            yAxis.setLabel(langPresenter.loggedInTime());
            LineChart<Number, Number> pastLoginTimesChart = new LineChart<>(xAxis, yAxis);
            pastLoginTimesChart.setTitle(langPresenter.pastLoginDurations());
                // create and load values into chart
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            List<Double> times = presenter.getPastLoginTimes();
            int xLoginsAgo = times.size();
            for (Double time : times){
                series.getData().add(new XYChart.Data<>(xLoginsAgo, time*60));
                xLoginsAgo = xLoginsAgo - 1;
            }
            pastLoginTimesChart.getData().add(series);
            GridPane.setConstraints(pastLoginTimesChart, 0, 1);
            bottomMenu.getChildren().add(pastLoginTimesChart);

        });

        Button logoutButton = new Button(langPresenter.logoutButton());
        logoutButton.setOnAction(actionEvent -> {
            bottomMenu.getChildren().clear();
            lc.logoutUser();
            MainView.setLoginScene();
        });

        // Add buttons to Top Menu and sets properties
        topMenu.getChildren().addAll(button1, button2, button3, button4, button5, button6, logoutButton);

        // Set properties of the bottom menu
        bottomMenu.setAlignment(Pos.TOP_LEFT);
        bottomMenu.getChildren().add(txtObj);
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
        MainView.getStage().setTitle(langPresenter.AttendeePanel());
    }
}
