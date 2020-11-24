package com.group0179.scenes;

import com.group0179.MainView;
import com.group0179.filters.LoginFilter;
import com.group0179.presenters.LoginPresenter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Justin Chan
 */

public class LoginScene implements IScene {
    LoginFilter filter;
    LoginPresenter presenter;

    Scene mainPanel;

    public LoginScene(LoginFilter filter, LoginPresenter presenter) {
        this.filter = filter;
        this.presenter = presenter;
    }

    @Override
    public void setScene() {
        // Layout and scene for Full View
        BorderPane main = new BorderPane();
        mainPanel = new Scene(main, x, y);

        // Layout and scene for Menu
        HBox topMenu = new HBox();
        topMenu.setSpacing(10);

        // Layout and scene for Login
        GridPane loginMenu = new GridPane();
        loginMenu.setVgap(2.5);
        loginMenu.setHgap(2.5);

        // Layout and scene for Account Creation
        GridPane createAccount = new GridPane();
        createAccount.setVgap(2.5);
        createAccount.setHgap(2.5);

        // Elements for the Main Menu
        Button loginMenuButton = new Button("Login");
        loginMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(loginMenu);
                MainView.getStage().setTitle("Login Menu: Login");
            }
        });

        Button button2 = new Button("Create Account"); // TODO:
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(createAccount);
                MainView.getStage().setTitle("Login Menu: Create Account");
            }
        });


        // Elements for Login Menu
        Label inputNamePrompt = new Label("Username: ");
        TextField inputName = new TextField();

        Label loginFailedLabel = new Label("User was not found.");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loginMenu.getChildren().remove(loginFailedLabel);
                if (!false) { // TODO
                    loginMenu.add(loginFailedLabel, 5, 7, 2, 1);
                }
            }
        });


        // Elements for Account Creation
        Label inputNewNamePrompt = new Label("Username: ");
        TextField inputNewName = new TextField();

        Label creationSuccessLabel = new Label("User created successfully..");
        Label creationFailedLabel = new Label("Username already exists.");

        Button createAccountButton = new Button("Create");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createAccount.getChildren().remove(creationSuccessLabel);
                createAccount.getChildren().remove(creationFailedLabel);
                if (!false) { // TODO
                    createAccount.add(creationSuccessLabel, 5, 7, 2, 1);
                } else {
                    createAccount.add(creationFailedLabel, 5, 7, 2, 1);
                }
            }
        });


        // Set initial scene for Main Panel
        main.setTop(topMenu);

        // Add buttons to Top Menu
        topMenu.getChildren().addAll(loginMenuButton, button2);

        // Add buttons to Login Menu
        loginMenu.add(loginButton, 7, 5);
        loginMenu.add(inputNamePrompt, 5, 5);
        loginMenu.add(inputName, 6, 5);

        // Add buttons to Create Account
        createAccount.add(createAccountButton, 7, 5);
        createAccount.add(inputNewNamePrompt, 5, 5);
        createAccount.add(inputNewName, 6, 5);


        // Setup and Start Initial Scene: Main Menu
        MainView.getStage().setScene(mainPanel);
        MainView.getStage().setTitle("Login Panel");
    }
}
