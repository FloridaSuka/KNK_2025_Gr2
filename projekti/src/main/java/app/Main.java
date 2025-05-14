package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Ngarkon skedarin FXML
        Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));

        // Vendos titullin e dritares
        primaryStage.setTitle("EduMetrics - Login");

        // Krijon skenën me përmasat 800x500 (ashtu siç e ke në FXML)
        primaryStage.setScene(new Scene(root, 800, 500));

        // Shfaq dritaren
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
