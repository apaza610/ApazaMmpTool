package com.apaza.apazammptool;
import com.dustinredmond.fxtrayicon.FXTrayIcon;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class App extends Application {
    static String cadenaApaza = "0 0";
    static String cadenaMath = "1 2";
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ScnApp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 200);
        scene.getStylesheets().add(getClass().getResource("extilo.css").toExternalForm());
        stage.setTitle("ApzTool");
        Image icono = new Image(getClass().getResourceAsStream("/imgs/icon.png"));
        stage.getIcons().add(icono);

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

    public static void main(String[] args) throws FileNotFoundException {
        File apzTxt = new File("src/main/resources/glifos.txt");
        Scanner apzScan = new Scanner(apzTxt);
        cadenaApaza = apzScan.nextLine();
        cadenaMath = apzScan.nextLine();

        System.out.println(cadenaApaza);
        System.out.println(cadenaMath);

        launch();
    }
}