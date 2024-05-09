module com.example.selection {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.selection to javafx.fxml;
    exports com.example.selection;
}