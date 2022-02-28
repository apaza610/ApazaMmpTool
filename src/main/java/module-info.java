module com.apaza.apazammptool {
    requires javafx.controls;
    requires javafx.fxml;
    requires FXTrayIcon;


    opens com.apaza.apazammptool to javafx.fxml;
    exports com.apaza.apazammptool;
}