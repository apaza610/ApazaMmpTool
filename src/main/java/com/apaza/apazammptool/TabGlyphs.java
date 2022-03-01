package com.apaza.apazammptool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class TabGlyphs {

    @FXML    private TextField tfSalida;

    @FXML    void onSouji() {
        tfSalida.setText("");
    }
    @FXML    void onMouseClicked(MouseEvent event) {
        String temp = event.getSource().toString();
        char elgliph = temp.charAt(temp.length() - 2);  //α Β β
        temp = tfSalida.getText();
        tfSalida.setText(temp += elgliph);
    }
    @FXML    void onClipboard() {
        String cadena = tfSalida.getText();
        StringSelection seleccion = new StringSelection(cadena);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(seleccion, null);
    }

}
