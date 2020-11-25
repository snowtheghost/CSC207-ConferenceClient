package com.group0179.scenes;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * @author Justin Chan
 */

public interface IScene {
    int x = 400;
    int y = 300;

    BorderPane main = new BorderPane();
    HBox topMenu = new HBox();
    HBox emptyBottomMenu = new HBox();

    void setScene();
}