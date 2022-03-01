package com.apaza.apazammptool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TabPaths {

    @FXML    private RadioButton rbGnuHttp;
    @FXML    private RadioButton rbHttpGnu;
    @FXML    private RadioButton rbHttpWin;
    @FXML    private RadioButton rbWinHttp;
    @FXML    private TextArea taSalida;
    @FXML    private TextField tfEntrada;

    @FXML    void onSouji() {
        tfEntrada.setText("");  tfEntrada.requestFocus();
    }
    @FXML    void onExecute() {
        String cadena = tfEntrada.getText();
        if( rbWinHttp.isSelected() ){
            cadena = cadena.replace("E:\\apz","http:\\\\192.168.1.12\\apz");
            cadena = cadena.replace("\\","/");
        }else if( rbHttpWin.isSelected() ){
            cadena = cadena.replace("/","\\");
            cadena = cadena.replace("http:\\\\192.168.1.12\\apz","E:\\apz");
        }
        else if( rbGnuHttp.isSelected() ) cadena = cadena.replace("/mnt/dsc4t/apz","http://192.168.1.12/apz");
        else if( rbHttpGnu.isSelected() ) cadena = cadena.replace("http://192.168.1.12/apz","/mnt/dsc4t/apz");
        taSalida.setText(cadena);
    }
    @FXML
    void onKeyReleased() {
        if( tfEntrada.getText().startsWith("E") ){
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
        if( elPath.startsWith("E") ){
            Runtime.getRuntime().exec("explorer.exe /select," + taSalida.getText());
        }else if( elPath.startsWith("h") ){
            if(Desktop.isDesktopSupported()) Desktop.getDesktop().browse(new URI(elPath));
        }
    }
}
