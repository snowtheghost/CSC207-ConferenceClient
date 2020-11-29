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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * LoginScene implementation. Responsible
 * for handling user login and account creation.
 * @author Zachariah Vincze
 */

public class LoginScene implements IScene {
    LoginFilter filter;
    LoginPresenter presenter;
    BorderPane borderPane;
    Scene mainScene;

    // Buttons
    Button loginScreenBtn;
    Button createAccountScreenBtn;

    Button loginBtn;
    Button createAccountBtn;

    // TextFields
    TextField loginTextField;
    TextField createAccountTextField;

    // Grids
    GridPane loginGrid;
    GridPane createAccountGrid;

    // Top menu
    HBox topMenu;

    private void changeToLoginPane() {
        borderPane.setCenter(loginGrid);
    }

    private void changeToCreateAccountPane() {
        borderPane.setCenter(createAccountGrid);
    }

    private void constructScene() {
        loginScreenBtn = new Button("Login");
        createAccountScreenBtn = new Button("Create Account");
        topMenu = new HBox();
        topMenu.getChildren().addAll(loginScreenBtn, createAccountScreenBtn);

        borderPane = new BorderPane();
        borderPane.setTop(topMenu);

        // Create text fields
        loginTextField = new TextField();
        createAccountTextField = new TextField();

        // Create buttons
        loginBtn = new Button("Login");
        createAccountBtn = new Button("Create account");

        // Construct login grid
        loginGrid = new GridPane();
        loginGrid.add(loginTextField, 0, 0);
        loginGrid.add(loginBtn, 1, 0);

        // Construct create account grid
        createAccountGrid = new GridPane();
        createAccountGrid.add(createAccountTextField, 0, 0);
        createAccountGrid.add(createAccountBtn, 1, 0);

        // Set the default main grid
        borderPane.setCenter(loginGrid);

        mainScene = new Scene(borderPane, x, y);
    }

    public LoginScene(LoginFilter filter, LoginPresenter presenter) {
        this.filter = filter;
        this.presenter = presenter;
    }

    @Override
    public void setScene() {
        constructScene();
        MainView.getStage().setScene(mainScene);
    }
}
