package com.apaza.apazammptool;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class ScnApp implements Initializable {

    @FXML    private TextField tfURLpath;
    @FXML    private WebView wvNavegador;
    private WebEngine motor;

    @FXML    void goToWebsite() {
        motor.load(tfURLpath.getText());
    }
    @FXML    void goToHome() {
        motor.load("http://192.168.1.12/apz/");
    }
    @Override    public void initialize(URL location, ResourceBundle resources) {
        motor = wvNavegador.getEngine();
        goToWebsite();
    }
}