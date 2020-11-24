package com.group0179.scenes;

import com.group0179.filters.OrganizerFilter;
import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;
import javafx.application.Application;
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
 * @author Template: Justin Chan
 */

public class TemplateView extends Application {
    // Temporary direct creations of Use Cases
    UserManager um = new UserManager();
    RoomManager rm = new RoomManager();
    MessageManager mm = new MessageManager();
    OrganizerFilter filter = new OrganizerFilter(um, rm ,mm);

    Stage window;
    Scene mainPanel;
    int x = 400;
    int y = 300; // window dimensions

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;

        // Layout and scene for Full View
        BorderPane main = new BorderPane();
        mainPanel = new Scene(main, x, y);

        // Layout and scene for Menu
        HBox topMenu = new HBox();
        topMenu.setSpacing(10);

        // Layout and scene for Command 1
        GridPane layout1 = new GridPane();

        // Layout and scene for Command 2
        GridPane layout2 = new GridPane();
        layout2.setVgap(2.5);
        layout2.setHgap(2.5);
        GridPane sublayout1 = new GridPane();
        sublayout1.setVgap(2.5);
        sublayout1.setHgap(2.5);

        // Elements for the Main Menu
        Button button1 = new Button ("Command 1");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(layout1);
            }
        });

        Button button2 = new Button ("Command 2");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(layout2);
            }
        });


        // Elements for Command 2
        Button menuButton1 = new Button ("Sub-Command 1");
        menuButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.setCenter(sublayout1);
            }
        });

        Label inputPrompt = new Label("Prompt: ");
        TextField inputField = new TextField();

        Label taskSuccessLabel = new Label("Task success");
        Label taskFailLabel = new Label("Task failed successfully");

        Button inputButton = new Button ("Go");
        inputButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });


        // Set initial scene for Main Panel
        main.setTop(topMenu);

        // Add buttons to Top Menu
        topMenu.getChildren().addAll(button1, button2);

        // Add buttons to Command 2 Menu
        layout2.add(menuButton1, 5, 5);
        sublayout1.add(inputPrompt, 5, 5);
        sublayout1.add(inputField, 6, 5);
        sublayout1.add(inputButton, 7, 5);


        // Setup and Start Initial Scene: Main Menu
        window.setScene(mainPanel);
        window.setTitle("X Panel: Stuff");
        window.show();

        // Exit script
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                windowEvent.consume();
                closeProgram();
            }
        });
    }

    private void closeProgram() {
        // TODO: Exit code goes here
        window.close();
    }
}
