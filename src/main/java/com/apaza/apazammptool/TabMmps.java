package com.apaza.apazammptool;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.stream.Stream;

public class TabMmps {
    @FXML    private Button btTiempo;
    @FXML    private Label lbTipoMedia;
    @FXML    private ToggleGroup grpMedia;
    @FXML    private RadioButton rbTipoPdf;
    @FXML    private RadioButton rbTipoVideo;
    @FXML    private TextArea taSalida;
    @FXML    private TextField tfHref;
    @FXML    private TextField tfLinkText;
    @FXML    private TextField tfNroVideo;
    @FXML    private TextField tfTiempo;
    //                       0                  1                   2        3        4      5     6        7          8
    String[] cadena = {"<a href=\"" , "http://www.blender.org" , "/?t=" , "0:0:9" , "&v=" , "0" , "\">", "......" , "</a>"};
    @FXML    void onSouji() {
        tfHref.setText(""); tfTiempo.setText(""); tfNroVideo.setText(""); tfLinkText.setText(""); tfHref.requestFocus();
    }
    @FXML    void onTimeConversion(){
        tfTiempo.setText( tfTiempo.getText().isEmpty()? "0:0:9" : conversorTime(tfTiempo.getText()) );
    }
    private String conversorTime(String tiempo){
        int[] t = Stream.of(tiempo.split(":")).mapToInt(Integer::parseInt).toArray();
        return String.valueOf(t[0]*3600 + t[1]*60 + t[2]);
    }
    @FXML    void onExecute() {
        cadena[1] = tfHref.getText();  cadena[7] = tfLinkText.getText();
        if( rbTipoVideo.isSelected() ){
            if(tfNroVideo.getText().isEmpty())  cadena[4] = cadena[5] = "";
            else cadena[5] = tfNroVideo.getText();

            if( tfTiempo.getText().isEmpty() )  cadena[2] = cadena[3] = "";
            else{
                if( tfNroVideo.getText().isEmpty() )    cadena[2] = "#t=";
                else{
                    cadena[2] = "?t=";
                    cadena[4] = "&v=";
                    cadena[5] = tfNroVideo.getText();
                }
                cadena[3] = tfTiempo.getText();
            }
        }else if( rbTipoPdf.isSelected() ){
            cadena[2] = cadena[3] = cadena[4] = cadena[5] = "";
            if( !tfNroVideo.getText().isEmpty() ){
                cadena[4] = "#page=";
                cadena[5] = tfNroVideo.getText();
            }
        }
        taSalida.setText(String.join("",cadena));
    }
    @FXML    void onKeyLinkReleased() {
        onExecute();
    }
    @FXML    void onKeyReleased() {
        if( tfHref.getText().endsWith("pdf") ){
            rbTipoPdf.setSelected(true);
        }else if(tfHref.getText().endsWith("mp4")){
            rbTipoVideo.setSelected(true);
        }else if(tfHref.getText().endsWith("/")){
            rbTipoVideo.setSelected(true);
        }
        onTipoMediaSelected();
    }
    @FXML    void onTipoMediaSelected() {
        if( rbTipoPdf.isSelected() ){
            lbTipoMedia.setText("page=");
            btTiempo.setVisible(false);
            tfTiempo.setVisible(false);
        }else if( rbTipoVideo.isSelected() ){
            lbTipoMedia.setText("v=");
            btTiempo.setVisible(true);
            tfTiempo.setVisible(true);
        }
    }
    @FXML    void onClipboard() {
        String cadena = taSalida.getText();
        StringSelection seleccion = new StringSelection(cadena);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(seleccion, null);
    }

}
