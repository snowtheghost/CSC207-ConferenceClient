package com.group0179.scenes;

import javafx.application.Application;

public abstract class IView extends Application {


    static int state = 1;  // TODO Change to 0

    static void setup() { }

    public static int getState() {
        return IView.state;
    }
}
