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
import java.util.Stack;

public class SceneNavigator {

    // Stack për të ruajtur historinë e skenave
    // Stack për të ruajtur historinë e skenave
    // Stack për të ruajtur historinë e skenave
    private static final Stack<String> history = new Stack<>();
    public static final String SETTINGS_PAGE = "/views/settings.fxml";
    public static void initializeHistory(String initialPath) {
        if (!history.contains(initialPath)) {
            System.out.println("✅ Ruajtja e path-it fillestar në histori: " + initialPath);
            history.push(initialPath);
        }
    }
    // Ndërrimi i skenës dhe ruajtja në histori
    public static void switchScene(Node node, String path) {
        try {
            // ✅ Marrim skenën aktuale
            Scene currentScene = node.getScene();
            if (currentScene != null) {
                Parent currentRoot = currentScene.getRoot();
                String currentPath = currentRoot.getId();

                if (currentPath == null) {
                    // ✅ Po e marrim nga skena dhe e ruajmë ID
                    currentPath = path;
                    currentRoot.setId(path);
                }

                // ✅ Ruajmë skenën në histori, por vetëm nëse nuk është aty
                if (!history.contains(currentPath)) {
                    System.out.println("📌 Ruaj në histori: " + currentPath);
                    history.push(currentPath);
                }
            }

            System.out.println("📌 Historia e skenave: " + history);

            // ✅ Ngarkimi i skenës së re
            URL resource = SceneNavigator.class.getResource(path);
            if (resource == null) {
                System.out.println("❌ Skedari nuk u gjet: " + path);
                return;
            }
            Parent view = FXMLLoader.load(resource);
            view.setId(path); // Ruajmë path-in si ID për histori
            Scene scene = new Scene(view);

            // Ndryshimi i skenës
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            System.out.println("✅ Skena u ngarkua me sukses: " + path);

        } catch (IOException e) {
            System.out.println("❌ Gabim gjatë ngarkimit të skenës: " + path);
            e.printStackTrace();
        }
    }

    // Funksioni për t'u kthyer një hap prapa
    public static void goBack(Node node) {
        try {
            System.out.println("📌 Historia aktuale: " + history);

            if (history.size() > 0) {
                // ✅ Merr skenën e mëparshme
                String previousPath = history.pop();
                System.out.println("🔄 Duke u kthyer në: " + previousPath);

                URL resource = SceneNavigator.class.getResource(previousPath);

                if (resource == null) {
                    System.out.println("❌ Skedari nuk u gjet: " + previousPath);
                    return;
                }

                Parent view = FXMLLoader.load(resource);
                view.setId(previousPath); // Ruajmë path-in si ID për histori
                Scene scene = new Scene(view);

                // Ndryshojmë skenën
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(scene);
                stage.show();

                System.out.println("✅ U kthye me sukses te: " + previousPath);

            } else {
                System.out.println("⚠️ Historia nuk ka më skena për t'u kthyer prapa.");
            }
        } catch (IOException e) {
            System.out.println("❌ Gabim gjatë kthimit prapa!");
            e.printStackTrace();
        }
    }
    public static final String LOGIN_PAGE = "/views/login.fxml";

        /**
         * Metoda për të bërë logout dhe të kalojë te faqja e login.
         *
         * @param node Një komponent nga skena aktuale për të marrë Stage.
         */
        public static void logout (Node node){
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

