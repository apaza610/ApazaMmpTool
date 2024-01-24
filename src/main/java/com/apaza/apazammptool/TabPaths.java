package com.apaza.apazammptool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class TabPaths implements Initializable {
    @FXML    private ChoiceBox<String> cbDiscos;
    @FXML    private RadioButton rbGnuHttp;
    @FXML    private RadioButton rbHttpGnu;
    @FXML    private RadioButton rbHttpWin;
    @FXML    private RadioButton rbWinHttp;
    @FXML    private TextArea taSalida;
    @FXML    private TextField tfEntrada;

//    Preferences prefs = Preferences.userNodeForPackage(ScnApp.class);
//    String disco = prefs.get("disco", "E");
    String valueDisco;
    @FXML    void onSouji() {
        tfEntrada.setText("");  tfEntrada.requestFocus();
    }
    @FXML    void onExecute() {
        String cadena = tfEntrada.getText();
        if( rbWinHttp.isSelected() ){
            cadena = cadena.replace(valueDisco + ":\\apz","http:\\\\localhost\\apz");
            cadena = cadena.replace("\\","/");
        }else if( rbHttpWin.isSelected() ){
            cadena = cadena.replace("/","\\");
            cadena = cadena.replace("http:\\\\localhost\\apz",valueDisco + ":\\apz");
        }
        else if( rbGnuHttp.isSelected() ) cadena = cadena.replace("/mnt/dsc4t/apz","http://localhost/apz");
        else if( rbHttpGnu.isSelected() ) cadena = cadena.replace("http://localhost/apz","/mnt/dsc4t/apz");
        taSalida.setText(cadena);
    }
    @FXML
    void onKeyReleased() {
        if( tfEntrada.getText().startsWith(valueDisco) ){
            rbWinHttp.setSelected(true);
            this.onExecute();
        }
        else if( tfEntrada.getText().startsWith("h") ) {
            rbHttpWin.setSelected(true);
            this.onExecute();
        }
    }
    @FXML    void onClipboard(ActionEvent event) {
        String cadena = taSalida.getText();
        StringSelection seleccion = new StringSelection(cadena);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(seleccion, null);
    }
    @FXML    void onGoto() throws URISyntaxException, IOException {
        String elPath = taSalida.getText();
        if( elPath.startsWith(valueDisco) ){
            Runtime.getRuntime().exec("explorer.exe /select," + taSalida.getText());
        }else if( elPath.startsWith("h") ){
            if(Desktop.isDesktopSupported()) Desktop.getDesktop().browse(new URI(elPath));
        }
    }
    @Override    public void initialize(URL location, ResourceBundle resources) {
        valueDisco = cbDiscos.getValue();
        rbWinHttp.setText(valueDisco + ": → http");
        rbHttpWin.setText("http: → " + valueDisco + ":");
        cbDiscos.setOnAction(this::getDisco);
    }
    public void getDisco(ActionEvent evento){
        valueDisco = cbDiscos.getValue();
        rbWinHttp.setText(valueDisco + ": → http");
        rbHttpWin.setText("http: → " + valueDisco + ":");
    }
}