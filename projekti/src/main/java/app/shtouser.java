package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.SceneLocator;

public class shtouser extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Ngarko pamjen fillestare
        Parent root = FXMLLoader.load(getClass().getResource(SceneLocator.ADD_USER_PAGE));

        // Vendos titullin e dritares
        primaryStage.setTitle("EduMetrics - Regjistrimi i Përdoruesit");

        // Krijo skenën dhe cakto madhësinë
        primaryStage.setScene(new Scene(root, 800, 500));

        // Shfaq dritaren
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
