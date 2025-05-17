package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NotatApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Ngarkimi i skedarit FXML
            Parent root = FXMLLoader.load(getClass().getResource("/views/MenaxhimiINotave.fxml"));
            primaryStage.setTitle("Sistemi i Vendosjes së Notave");
            primaryStage.setScene(new Scene(root, 800, 500));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
