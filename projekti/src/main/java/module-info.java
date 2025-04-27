module com.example.demo {
    requires java.sql;
    requires javafx.fxml;
    requires javafx.controls;

    opens com.example.demo to javafx.fxml;
    opens database to javafx.fxml;
    opens knk2025 to javafx.fxml;
    opens controllers to javafx.fxml;

    exports com.example.demo;
    exports controllers;
    exports database;
    exports knk2025;
}
