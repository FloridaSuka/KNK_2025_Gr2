package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.SceneLocator;

import java.util.Locale;
import java.util.ResourceBundle;

public class shtouser extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Load with consistent resource resolution
            ResourceBundle bundle = ResourceBundle.getBundle("bundles.Messages", Locale.getDefault());
            FXMLLoader loader = new FXMLLoader(
                    SceneLocator.class.getResource(SceneLocator.ADD_USER_PAGE),
                    bundle
            );

            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 500);

            // Verify CSS loading
            String cssPath = "/views/style.css";
            if (SceneLocator.class.getResource(cssPath) != null) {
                scene.getStylesheets().add(
                        SceneLocator.class.getResource(cssPath).toExternalForm()
                );
            } else {
                System.err.println("CSS file not found at: " + cssPath);
            }

            primaryStage.setTitle("EduMetrics - Regjistrimi i Përdoruesit");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Failed to load FXML: " + SceneLocator.ADD_USER_PAGE);
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}