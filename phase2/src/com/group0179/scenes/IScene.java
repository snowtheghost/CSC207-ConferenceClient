package com.group0179.scenes;

import com.group0179.filters.Filter;
import com.group0179.presenters.Presenter;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public interface IScene {
    int x = 400;
    int y = 300;

    BorderPane main = new BorderPane();
    HBox topMenu = new HBox();

    void setScene();
}