package com.apaza.apazammptool;
import com.dustinredmond.fxtrayicon.FXTrayIcon;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("ApzTool");

        FXTrayIcon tricon = new FXTrayIcon(stage, getClass().getResource("/imgs/icon.png"));
        tricon.setTrayIconTooltip("tool for mindmaps & anki");
        MenuItem mnMsg = new MenuItem("mostrar mensaje");
        MenuItem mnExit = new MenuItem("exit");
        mnMsg.setOnAction(e->new Alert(Alert.AlertType.INFORMATION, "menu clickeado").showAndWait());
        mnExit.setOnAction(e->{
            Platform.exit();
            System.exit(0);
        });
        Menu mnOpciones = new Menu("Opciones");
        MenuItem item1 = new MenuItem("item1");
        MenuItem item2 = new MenuItem("item2");
        item1.setOnAction(e-> System.out.println("menu / item1 elegido"));
        mnOpciones.getItems().addAll(item1, item2);

        tricon.addMenuItems(mnMsg, mnExit, mnOpciones);
        tricon.show();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}