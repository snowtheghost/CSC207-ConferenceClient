package com.group0179.scenes;

import com.group0179.MainView;
import com.group0179.controllers.LoginController;
import com.group0179.exceptions.InvalidCredentialsException;
import com.group0179.exceptions.UsernameTakenException;
import com.group0179.filters.LoginFilter;
import com.group0179.presenters.ILoginPresenter;
import com.group0179.presenters.LoginPresenterEN;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * LoginScene implementation. Responsible
 * for handling user login and account creation.
 * @author Zachariah Vincze
 */

public class LoginScene implements IScene {
    private final LoginFilter filter;
    private final ILoginPresenter presenter;
    private final LoginController controller;

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

    // Labels
    Label loginInfo;
    Label createAccountInfo;

    ChoiceBox<String> accountTypeChoiceBox;

    // Grids
    GridPane loginGrid;
    GridPane createAccountGrid;

    // Top menu
    HBox topMenu;

    public LoginScene(LoginFilter filter, ILoginPresenter presenter, LoginController controller) {
        this.filter = filter;
        this.presenter = presenter;
        this.controller = controller;
    }

    private void changeToLoginPane() {
        borderPane.setCenter(loginGrid);
        MainView.getStage().setTitle(presenter.loginButtonPrompt());
    }

    private void changeToCreateAccountPane() {
        borderPane.setCenter(createAccountGrid);
        MainView.getStage().setTitle(presenter.createAccountButtonPrompt());
    }

    private void attemptLogin() {
        String username = loginTextField.getText();
        try {
            String accountType = controller.loginUser(username);
            switch (accountType) {
                case "attendee":
                    MainView.setAttendeeScene(); break;
                case "organizer":
                    MainView.setOrganizerScene(); break;
                case "speaker":
                    MainView.setSpeakerScene(); break;
            }
        } catch (InvalidCredentialsException e) {
            loginInfo.setText(presenter.usernameNotFoundError(username));
        }
    }

    private void attemptAccountCreate() {
        String username = createAccountTextField.getText();
        String accountType = accountTypeChoiceBox.getValue();
        String accountChoice;
        if (accountType.equals(presenter.attendeeAccountChoice())) accountChoice = "attendee";
        else if (accountType.equals(presenter.organizerAccountChoice())) accountChoice = "organizer";
        else if (accountType.equals(presenter.speakerAccountChoice())) accountChoice = "speaker";
        else accountChoice = "vipattendee";
        try {
            controller.createAccount(accountChoice, username);
        } catch (UsernameTakenException e) {
            createAccountInfo.setText(presenter.usernameTakenError(username));
            return;
        }
        createAccountInfo.setText(presenter.accountCreationSuccess());
    }

    private void constructLoginGrid() {
        loginGrid = new GridPane();
        loginTextField = new TextField();
        loginBtn = new Button(presenter.loginButtonPrompt());
        loginBtn.setOnAction(actionEvent -> attemptLogin());
        loginInfo = new Label();

        // Layout components
        loginGrid.add(loginTextField, 0, 0);
        loginGrid.add(loginBtn, 1, 0);
        loginGrid.add(loginInfo, 0, 1);
    }

    private void constructCreateAccountGrid() {
        createAccountGrid = new GridPane();
        accountTypeChoiceBox = new ChoiceBox<>();
        accountTypeChoiceBox.getItems().addAll(presenter.attendeeAccountChoice(),
                presenter.organizerAccountChoice(),
                presenter.speakerAccountChoice(),
                presenter.vipAttendeeAccountChoice());
        accountTypeChoiceBox.setValue(presenter.attendeeAccountChoice());
        createAccountBtn = new Button(presenter.createAccountButtonPrompt());
        createAccountBtn.setOnAction(actionEvent -> attemptAccountCreate());
        createAccountTextField = new TextField();
        createAccountInfo = new Label();

        // Layout components
        createAccountGrid.add(accountTypeChoiceBox, 0, 0);
        createAccountGrid.add(createAccountTextField, 1, 0);
        createAccountGrid.add(createAccountBtn, 0, 1);
        createAccountGrid.add(createAccountInfo, 1, 1);
    }

    @Override
    public void constructScene() {
        loginScreenBtn = new Button(presenter.loginButtonPrompt());
        loginScreenBtn.setOnAction(actionEvent -> changeToLoginPane());
        createAccountScreenBtn = new Button(presenter.createAccountButtonPrompt());
        createAccountScreenBtn.setOnAction(actionEvent -> changeToCreateAccountPane());
        topMenu = new HBox();
        topMenu.getChildren().addAll(loginScreenBtn, createAccountScreenBtn);

        borderPane = new BorderPane();
        borderPane.setTop(topMenu);

        constructLoginGrid();
        constructCreateAccountGrid();

        // Set the default main grid
        changeToLoginPane();

        mainScene = new Scene(borderPane, x, y);
    }

    @Override
    public void setScene() {
        MainView.getStage().setScene(mainScene);
        MainView.getStage().setTitle(presenter.loginButtonPrompt());
    }
}
