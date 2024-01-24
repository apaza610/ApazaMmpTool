package com.apaza.apazammptool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

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

    @FXML    void savePreferencias(ActionEvent event) throws IOException, BackingStoreException {
        Preferences prefs = Preferences.userNodeForPackage(ScnApp.class);
        prefs.put("disco","F");
        prefs.exportNode(new FileOutputStream("prefs.xml"));
    }
    @FXML    void loadPreferencias(ActionEvent event) throws IOException, InvalidPreferencesFormatException {
        Preferences prefs = Preferences.userNodeForPackage(ScnApp.class);
        Preferences.importPreferences(new FileInputStream("prefs.xml"));
        System.out.println(prefs.get("disco","nones"));
    }
    @Override    public void initialize(URL location, ResourceBundle resources) {
        motor = wvNavegador.getEngine();
        goToWebsite();
    }
}