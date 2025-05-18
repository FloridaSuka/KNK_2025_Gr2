package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import utils.LanguageHandler;
import utils.SceneLocator;
import utils.SceneNavigator;

import java.io.IOException;

public class DrejtorController {

//    @FXML
//    private Button btnMesuesit;
//
//    @FXML
//    private Button btnKlaset;
//
//    @FXML
//    private Button btnStatistikat;
//
//    @FXML
//    private Button btnLendet;
//
//    @FXML
//    private Button btnProfile;
//
//    @FXML
//    private Button btnSignOut;
//
//    @FXML
//    private void initialize() {
//        System.out.println("✅ DrejtorController u inicializua!");
//
//        btnMesuesit.setOnAction(event -> switchScene(event, "/views/nxenesitView.fxml"));
//        btnKlaset.setOnAction(event -> switchScene(event, "/views/menaxhimiShkolles.fxml"));
//        btnStatistikat.setOnAction(event -> switchScene(event, "/views/MenaxhimiNotave.fxml"));
//        btnLendet.setOnAction(event -> switchScene(event, "/views/shtoUser.fxml"));
//        btnProfile.setOnAction(event -> switchScene(event, "/views/rivendosFjalekalimin.fxml"));
//        btnSignOut.setOnAction(event -> switchScene(event, "/views/login.fxml"));
//    }
//
//    private void switchScene(ActionEvent event, String fxmlPath) {
//        try {
//            System.out.println("📌 Duke tentuar të hapet: " + fxmlPath);
//            System.out.println("📌 Rruga absolute: " + getClass().getClassLoader().getResource(fxmlPath));
//
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlPath));
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.show();
//            System.out.println("✅ Skedari u hap me sukses: " + fxmlPath);
//        } catch (IOException e) {
//            System.out.println("❌ Nuk u gjet skedari: " + fxmlPath);
//            e.printStackTrace();
//        }
//    }
    //PJESA E GJUHES
    @FXML private MenuButton menuLanguage;

        @FXML public void initialize() {
            LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.ADMIN_PAGE);
        }
    @FXML
    private void handleLogout(ActionEvent event) {
        SceneNavigator.logout((Node) event.getSource());
    }

    @FXML
    private void handleSubject(ActionEvent event) {
        try {
            String path = SceneLocator.SUBJECT_MANAGEMENT_PAGE;

            // ✅ Ngarkojmë skenën e re nga path-i
            Parent lendetView = FXMLLoader.load(getClass().getResource(path));
            Scene lendetScene = new Scene(lendetView);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(lendetScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Gabim gjatë ngarkimit të skenës!");
        }
    }

    @FXML
    private void handleClass(ActionEvent event) {
        try {
            // 🔄 Marrim path-in nga SceneLocator
            String path = SceneLocator.CLASS_MANAGEMENT_PAGE;

            // ✅ Ngarkojmë skenën e re nga path-i
            Parent klasatView = FXMLLoader.load(getClass().getResource(path));
            Scene klasatScene = new Scene(klasatView);

            // 🔄 Marrim Stage aktual
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // 🔄 Ndryshojmë skenën në window
            window.setScene(klasatScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Gabim gjatë ngarkimit të skenës KlasatView.fxml");
        }
    }
}


