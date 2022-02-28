module com.apaza.apazammptool {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.apaza.apazammptool to javafx.fxml;
    exports com.apaza.apazammptool;
}