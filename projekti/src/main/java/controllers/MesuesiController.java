package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import utils.LanguageHandler;
import utils.SceneLocator;
import utils.SceneNavigator;

import java.io.IOException;

public class MesuesiController {

//    @FXML
//    private void btnNxenesi(ActionEvent event) {
//        loadFXML(event, "/views/nxenesiView.fxml");
//    }
//
//    @FXML
//    private void btnNota(ActionEvent event) {
//        loadFXML(event, "/views/MenaxhimiINotave.fxml");
//    }
//
//    @FXML
//    private void btnStatistikat(ActionEvent event) {
//        loadFXML(event, "/views/StatistikaView.fxml");
//    }
//
//    @FXML
//    private void btnMungesat(ActionEvent event) {
//        loadFXML(event, "/views/MenaxhimiMungesave.fxml");
//    }
//
//    @FXML
//    private void btnProfile(ActionEvent event) {
//        loadFXML(event, "/views/ProfileView.fxml");
//    }
//
//    @FXML
//    private void btnSignOut(ActionEvent event) {
//        loadFXML(event, "/views/login.fxml");
//    }
//
//    private void loadFXML(ActionEvent event, String fxmlPath) {
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (IOException e) {
//            System.out.println("❌ Nuk u gjet skedari: " + fxmlPath);
//            e.printStackTrace();
//        }
//    }
    @FXML private MenuButton menuLanguage;

    @FXML public void initialize() {
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.TEACHER_PAGE);
    }
    @FXML
    private void handleLogout(ActionEvent event) {
        SceneNavigator.logout((Node) event.getSource());
    }
}