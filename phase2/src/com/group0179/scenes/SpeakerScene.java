package com.group0179.scenes;

import com.group0179.MainView;
import com.group0179.controllers.LoginController;
import com.group0179.filters.SpeakerFilter;
import com.group0179.presenters.SpeakerPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Template: Justin Chan
 */

public class SpeakerScene implements IScene {
    SpeakerFilter filter;
    SpeakerPresenter presenter;
    LoginController lc;

    Scene mainPanel;
    GridPane menu1 = new GridPane();
    GridPane menu2 = new GridPane();
    GridPane menu2sub1 = new GridPane();
    BorderPane main = new BorderPane();
    //HBox topMenu = new HBox();
    private GridPane bottomMenu;
    private FlowPane topMenu;
    HBox emptyBottomMenu = new HBox();




    public SpeakerScene(SpeakerFilter filter, SpeakerPresenter presenter, LoginController lc) {
        //bottomMenu = new GridPane();
        //topMenu = new FlowPane();
        this.filter = filter;
        this.presenter = presenter;
        this.lc = lc;
    }

    private Text txtObjCreator(String text, double objectLength){
        Text label1 = new Text(text);
        label1.setWrappingWidth(objectLength);
        return label1;
    }

    private void displayForm(Text label, TextField textField, GridPane gridPane, int gridIndex){
        GridPane.setConstraints(label, 0, gridIndex);
        gridPane.getChildren().add(label);
        GridPane.setConstraints(textField, 0, gridIndex+1);
        gridPane.getChildren().add(textField);

    }

    public void constructScene() {
        bottomMenu = new GridPane();
        topMenu = new FlowPane();
        // Layout and scene for Full View
        mainPanel = new Scene(main, x, y);
        SpeakerScene speakerScene = this;

        // Layout and scene for Menu
        //topMenu.setSpacing(10);

        menu2.setVgap(2.5);
        menu2.setHgap(2.5);
        menu2sub1.setVgap(2.5);
        menu2sub1.setHgap(2.5);
        ListView<String> viewEventList = new ListView<>();
        ListView<String> viewSpeakingEventList = new ListView<>();

        ListView<String> speakerManager = new ListView<>();
        HBox msgMenu = new HBox(); msgMenu.setSpacing(5); msgMenu.setPadding(new Insets(10, 10, 10, 10));
        // Create Speaker
        GridPane msgview = new GridPane(); msgview.setVgap(5); msgview.setHgap(5); msgview.setPadding(new Insets(0, 10, 0, 10));
        GridPane msgviewBottom = new GridPane(); msgview.setVgap(5); msgview.setHgap(5); msgview.setPadding(new Insets(0, 10, 0, 10));


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

        Button viewMessagesButton = new Button("View Messages");
        viewMessagesButton.setOnAction(actionEvent -> {
            main.setCenter(msgview);
            main.setBottom(msgviewBottom);
            MainView.getStage().setTitle("View Messages");
        });

        Button button1 = new Button("Send message");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();

                // Creates labels and input space for form
                Text label1 = new Text("Enter username of who you would like to message.");
                Text label2 = new Text("Enter message content");
                TextField textField1 = new TextField();
                TextField textField2 = new TextField();

                // adds those to pane so it can be displayed
                displayForm(label1, textField1, bottomMenu, 0);
                displayForm(label2, textField2, bottomMenu, 2);

                // adds send button and result message to pane
                Button submitButton = new Button("Send");
                Text result = new Text("");
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

        Button button2 = new Button("View messages from username");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenu.getChildren().clear();


                // adds input box
                //Text label = speakerScene.txtObjCreater("Enter username of who's messages you would like to see, enter 'all' for all.", x/1.5);
                Text label = new Text("Enter Username:");
                TextField textField = new TextField();
                displayForm(label, textField, bottomMenu, 0);

                // adds submit button and result
                Text result = new Text("[autocomplete can go here]");
                Button submitButton = new Button("Submit");
                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        // adds result when button pressed
                        result.setText(presenter.viewMessagess(textField.getText()));
                    }
                });
                GridPane.setConstraints(submitButton, 0, 3);
                bottomMenu.getChildren().add(submitButton);
                GridPane.setConstraints(result, 0, 4);
                bottomMenu.getChildren().add(result);


            }
        });



        Button reply1Button = new Button("Reply");
        reply1Button.setOnAction(actionEvent -> {
            //main.setCenter(msgview);

            MainView.getStage().setTitle("Reply to Message 1");
        });

        Button reply2Button = new Button("Reply");
        reply2Button.setOnAction(actionEvent -> {
            //main.setCenter(msgview);
            MainView.getStage().setTitle("Reply to Message 2");
        });

        Button reply3Button = new Button("Reply");
        reply3Button.setOnAction(actionEvent -> {
            //main.setCenter(msgview);
            MainView.getStage().setTitle("Reply to Message 3");
        });

        Button reply4Button = new Button("Reply");
        reply4Button.setOnAction(actionEvent -> {
            //main.setCenter(msgview);
            MainView.getStage().setTitle("Reply to Message 4");
        });

        Button reply5Button = new Button("Reply");
        reply5Button.setOnAction(actionEvent -> {
            //main.setCenter(msgview);
            MainView.getStage().setTitle("Reply to Message 5");
        });

        Button reply6Button = new Button("Reply");
        reply6Button.setOnAction(actionEvent -> {
            //main.setCenter(msgview);
            MainView.getStage().setTitle("Reply to Message 6");
        });

        Button reply7Button = new Button("Reply");
        reply7Button.setOnAction(actionEvent -> {
            //main.setCenter(msgview);
            MainView.getStage().setTitle("Reply to Message 7");
        });

        Button reply8Button = new Button("Reply");
        reply8Button.setOnAction(actionEvent -> {
            //main.setCenter(msgview);
            MainView.getStage().setTitle("Reply to Message 8");
        });


        Button logoutButton = new Button("Log out");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lc.logoutUser();
                MainView.setLoginScene();
            }
        });

        // Set initial scene for Main Panel
        main.setTop(topMenu);

        // Add buttons to Top Menu
        topMenu.getChildren().addAll(viewEventsButton, viewSpeakingEventsButton, button1, viewMessagesButton, logoutButton);

        //System.out.println(presenter.viewMessagess("LiamA"));

        int i = 0;

        for (Iterator<Map.Entry<UUID, List<String>>> it = this.presenter.viewMessages(); it.hasNext(); ) {
            System.out.println(this.presenter.viewMessages());
            Map.Entry<UUID, List<String>> key = it.next();
            //System.out.println(key);
            //System.out.println(key.getKey());
            //System.out.println(key.getValue());
            if (i == 0){
                Label sender1 = new Label(presenter.getUsername(key.getKey()));
                Label msg1 = new Label(key.getValue().get(0));
                msgview.add(reply1Button, 0, 0);
                sender1.setStyle("-fx-font-weight: bold");
                msgview.add(sender1, 1, 0);
                msgview.add(msg1, 2, 0);
            }
            if (i == 1){
                Label sender2 = new Label(presenter.getUsername(key.getKey()));
                Label msg2 = new Label(key.getValue().get(0));
                msgview.add(reply2Button, 0, 1);
                sender2.setStyle("-fx-font-weight: bold");
                msgview.add(sender2, 1, 1);
                msgview.add(msg2, 2, 1);
            }
            if (i == 2){
                Label sender3 = new Label(presenter.getUsername(key.getKey()));
                Label msg3 = new Label(key.getValue().get(0));
                msgview.add(reply3Button, 0, 2);
                sender3.setStyle("-fx-font-weight: bold");
                msgview.add(sender3, 1, 2);
                msgview.add(msg3, 2, 2);
            }
            if (i == 3){
                Label sender4 = new Label(presenter.getUsername(key.getKey()));
                Label msg4 = new Label(key.getValue().get(0));
                msgview.add(reply4Button, 0, 3);
                sender4.setStyle("-fx-font-weight: bold");
                msgview.add(sender4, 1, 3);
                msgview.add(msg4, 2, 3);
            }
            if (i == 4){
                Label sender5 = new Label(presenter.getUsername(key.getKey()));
                Label msg5 = new Label(key.getValue().get(0));
                msgview.add(reply5Button, 0, 4);
                sender5.setStyle("-fx-font-weight: bold");
                msgview.add(sender5, 1, 4);
                msgview.add(msg5, 2, 4);
            }
            if (i == 5){
                Label sender6 = new Label(presenter.getUsername(key.getKey()));
                Label msg6 = new Label(key.getValue().get(0));
                msgview.add(reply6Button, 0, 5);
                sender6.setStyle("-fx-font-weight: bold");
                msgview.add(sender6, 1, 5);
                msgview.add(msg6, 2, 5);
            }
            if (i == 6){
                Label sender7 = new Label(presenter.getUsername(key.getKey()));
                Label msg7 = new Label(key.getValue().get(0));
                msgview.add(reply7Button, 0, 6);
                sender7.setStyle("-fx-font-weight: bold");
                msgview.add(sender7, 1, 6);
                msgview.add(msg7, 2, 6);
            }
            if (i == 7){
                Label sender8 = new Label(presenter.getUsername(key.getKey()));
                Label msg8 = new Label(key.getValue().get(0));
                msgview.add(reply8Button, 0, 7);
                sender8.setStyle("-fx-font-weight: bold");
                msgview.add(sender8, 1, 7);
                msgview.add(msg8, 2, 7);
            }



            i++;
        }

        msgviewBottom.add(button2, 1, 9);

        bottomMenu.setAlignment(Pos.TOP_LEFT);
        //bottomMenu.getChildren().add(this.txtObj);
        int maxWidth = (x/3)*2;
        bottomMenu.setMaxWidth(maxWidth);
        bottomMenu.setVgap(5);
        bottomMenu.setHgap(5);

        main.setLeft(bottomMenu);
    }



    public void setScene() {
        MainView.getStage().setScene(mainPanel);
        MainView.getStage().setTitle("X Panel: Main Menu");
    }
}
