package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class SceneNavigator {
    public static void switchScene(Node node, String path) {
        try {

            URL resource = SceneNavigator.class.getResource(path);

            Parent view = FXMLLoader.load(resource);
            Scene scene = new Scene(view);

            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            System.out.println("✅ U kalua me sukses në skenën: " + path);

        } catch (IOException e) {
            System.out.println("❌ Gabim gjatë ngarkimit të skenës: " + path);
            e.printStackTrace();
        }
    }
    public static final String LOGIN_PAGE = "/views/login.fxml";

    /**
     * Metoda për të bërë logout dhe të kalojë te faqja e login.
     *
     * @param node Një komponent nga skena aktuale për të marrë Stage.
     */
    public static void logout(Node node) {
        // 🔹 Konfirmimi para se të bëhet logout
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmim");
        alert.setHeaderText("Dëshironi të dilni nga llogaria?");
        alert.setContentText("Kliko OK për të vazhduar, ose Cancel për të anuluar.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("👉 Po bëhet logout...");
            Stage stage = (Stage) node.getScene().getWindow();
            Locale locale = Locale.getDefault();
            ResourceBundle bundle = ResourceBundle.getBundle("bundles.Messages", locale);

            FXMLLoader loader = new FXMLLoader(SceneNavigator.class.getResource(LOGIN_PAGE), bundle);

            try {
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("EduMetrics - Login");
                stage.show();
                System.out.println("✅ U ridrejtua me sukses te faqja e login.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("❌ Gabim gjatë ngarkimit të faqes së login.");
            }
        } else {
            System.out.println("🔄 Logout u anulua.");
        }
    }
}
