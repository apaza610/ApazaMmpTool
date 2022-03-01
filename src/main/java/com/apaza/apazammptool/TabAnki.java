package com.apaza.apazammptool;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class TabAnki {

    @FXML    private CheckBox cbHiperlink;
    @FXML    private CheckBox cbIframe;
    @FXML    private RadioButton rbDevicePhone;
    @FXML    private RadioButton rbDeviceRaspi;
    @FXML    private RadioButton rbServerLoopback;
    @FXML    private RadioButton rbServerPC;
    @FXML    private RadioButton rbServerPhone;
    @FXML    private TextArea taSalida;
    @FXML    private TextField tfGameURL;

    String cadena1 = "", cadena2 = "", cadena3 = "";
    @FXML    void onSouji() {
        tfGameURL.setText("");
    }
    @FXML    void onExecute() {
        String ancho = rbDeviceRaspi.isSelected()? "480": "323";
        String alto = rbDeviceRaspi.isSelected()? "320": "216";
        String gameURL = tfGameURL.getText();
        if( rbServerPC.isSelected() ){
            gameURL = gameURL.replace(":8080", "");
            gameURL = gameURL.replace("127.0.0.1", "192.168.1.12");
        }
        else if( rbServerLoopback.isSelected() ){
            gameURL = gameURL.replace("192.168.1.12", "127.0.0.1");
        }
        else if( rbServerPhone.isSelected() ){
            gameURL = gameURL.replace("192.168.1.12", "127.0.0.1:8080");
        }
        if( cbIframe.isSelected() ){
            cadena1 = String.format("<iframe src=\"%s\" frameborder=\"1\" scrolling=\"no\" width=\"%s\" height=\"%s\"></iframe>", gameURL, ancho, alto);
        }else{
            cadena1 = "";
        }
        if( cbHiperlink.isSelected() ){
            cadena2 = String.format("<a href=\"%s\">\uD83D\uDD79️️</a>", gameURL);
        }else{
            cadena2 = "";
        }
        taSalida.setText(cadena1 + cadena2);
    }
    @FXML    void onKeyReleased() {
        onExecute();
    }
    @FXML    void onClipboard() {
        StringSelection seleccion = new StringSelection(taSalida.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(seleccion, null);
    }
}
