module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    opens Database to javafx.fxml;
    exports Database;
    opens KNK_2025 to javafx.fxml;
    exports KNK_2025;
}