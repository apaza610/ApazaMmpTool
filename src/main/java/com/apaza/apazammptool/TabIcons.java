package com.apaza.apazammptool;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TabIcons {

    @FXML    private ToggleGroup grpTipoApp;
    @FXML    private RadioButton rbAnki;
    @FXML    private RadioButton rbWeb;
    @FXML    private Label lbImgSrc;
    @FXML    private Spinner<String> spClase;
    @FXML    private Spinner<String> spColor;
    @FXML    private TextArea taSalida;
    @FXML    private TextField tfGlifo;
    @FXML    private TextField tfImgName;

    @FXML    void onSouji(ActionEvent event) {
        tfGlifo.setText("");    tfImgName.setText("");
        spClase.getValueFactory().setValue("");
        spColor.getValueFactory().setValue("");
    }
    @FXML    void onExecute() {
        if( !tfImgName.getText().isEmpty() ){
            String raiz = rbAnki.isSelected()? "" : "/";
            String cadena = String.format("<img src=\"%sapz/0assets/icons/apz/%s.svg\" class=\"icono\">", raiz, tfImgName.getText());
            taSalida.setText(cadena);
        }
        if( !tfGlifo.getText().isEmpty() ){
            //                     0        1        2     3           4           5     6   7    8      9
            String[] cadena = {"<span"," class=\"","???","\" "," style=\"color: ","???","\"",">","???","</span>"};
            if( spClase.getValue() != "" ){
                cadena[2] = spClase.getValue();
            }else{
                cadena[1] = cadena[2] = cadena[3] = "";
            }
            if( spColor.getValue() != "" ){
                cadena[5] = spColor.getValue();
            }else{
                cadena[4] = cadena[5] = cadena[6] = "";
            }
            cadena[8] = tfGlifo.getText();
            taSalida.setText(String.join("", cadena));
        }
    }
    @FXML    void onClipboard(ActionEvent event) {
        String cadena = taSalida.getText();
        StringSelection seleccion = new StringSelection(cadena);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(seleccion, null);
    }
    @FXML   void cambioTipoApp(){
        lbImgSrc.setText(rbAnki.isSelected()? "<img src=apz/0assets/icons/apz/" : "<img src=/apz/0assets/icons/apz/");
        onExecute();
    }
    @FXML   void onKeyReleased(){
        onExecute();
    }
}
