package com.group0179;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppMainView extends Application {

    Stage window;
    Scene scene1, scene2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Label label1 = new Label("Scene 1");
        Button button1 = new Button("Go to Scene 2");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                window.setScene(scene2); // Set window to scene2
            }
        });

        // Create layout for scene1
        VBox layout1 = new VBox(20); // Vertical column
        layout1.getChildren().addAll(label1, button1); // Add elements
        scene1 = new Scene(layout1, 600, 900); // set scene1

        // Button 2
        Button button2 = new Button("Go to Scene 1");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                window.setScene(scene1);
            }
        });

        // Create layout for scene2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600, 900);

        window.setScene(scene1); // set initial scene
        window.setTitle("Application View"); // set window title
        window.show(); // show window
    }
}
