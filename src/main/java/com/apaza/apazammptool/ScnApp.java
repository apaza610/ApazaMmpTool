package com.apaza.apazammptool;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScnApp {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}