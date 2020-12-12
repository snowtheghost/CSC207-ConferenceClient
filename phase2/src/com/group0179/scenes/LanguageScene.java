package com.group0179.scenes;

import com.group0179.MainView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class LanguageScene implements IScene{
    Button EnglishButton;
    Button ChineseButton;
    BorderPane borderPane;
    Scene mainScene;
    HBox topMenu;


    /**
     * construct language scene.
     */
    @Override
    public void constructScene() {
        EnglishButton = new Button("English");
        EnglishButton.setOnAction(actionEvent -> {
            MainView.constructScenes();
            MainView.setLoginScene();
        });
        ChineseButton = new Button("中文");
        ChineseButton.setOnAction(actionEvent ->{
            MainView.setLanguage("Chinese");
            MainView.constructScenes();
            MainView.setLoginScene();
        });
        topMenu = new HBox();
        topMenu.getChildren().addAll(EnglishButton, ChineseButton);
        borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        mainScene = new Scene(borderPane, x, y);
    }

    /**
     * set language scene.
     */
    @Override
    public void setScene() {
        MainView.getStage().setScene(mainScene);
        MainView.getStage().setTitle("English / 中文");}
}
