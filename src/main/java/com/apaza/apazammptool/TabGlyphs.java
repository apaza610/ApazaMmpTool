package com.apaza.apazammptool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TabGlyphs {
    @FXML    private FlowPane fpMath;
    @FXML    private FlowPane fpApz;
    @FXML    private TextField tfSalida;
    private String cdnApaza="abc", cdnMath = "456";
    private Button elboton;

    @FXML
    void leerApzGlyphs() {
        fpApz.getChildren().clear();
        for (int i = 0; i < cdnApaza.length(); i++) {
            elboton = new Button( Character.toString( cdnApaza.charAt(i) ) );
//            elboton.setStyle("-fx-font-family: ApazaMmp;");
            elboton.setStyle("-fx-font: 16 ApazaMmp;");
//            elboton.setPadding(Insets.EMPTY);
            elboton.setPadding(new Insets(2));
            fpApz.getChildren().add(elboton);
            elboton.setOnAction(event -> {
                String temp = event.getSource().toString();
                char elgliph = temp.charAt(temp.length()-2);    //α Β β
                temp = tfSalida.getText();
                tfSalida.setText(temp += elgliph);
            });
        }
    }

    @FXML
    void leerMathGlyphs() throws FileNotFoundException {
        File apzTxt = new File("src/main/resources/glifos.txt");
        Scanner apzScan = new Scanner(apzTxt);
        cdnApaza = apzScan.nextLine();
        cdnMath = apzScan.nextLine();

        fpMath.getChildren().clear();
        for (int i = 0; i < cdnMath.length(); i++) {
            elboton = new Button(Character.toString(cdnMath.charAt(i)));
            elboton.setStyle("-fx-font: 14 Arial;");
            fpMath.getChildren().add(elboton);
            elboton.setOnAction(event -> {
                String temp = event.getSource().toString();
                char elgliph = temp.charAt(temp.length()-2);
                temp = tfSalida.getText();
                tfSalida.setText(temp += elgliph);
            });
        }
    }

    @FXML    void onSouji() {         tfSalida.setText("");     }

    @FXML    void onClipboard() {
        String cadena = tfSalida.getText();
        StringSelection seleccion = new StringSelection(cadena);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(seleccion, null);
    }
}
