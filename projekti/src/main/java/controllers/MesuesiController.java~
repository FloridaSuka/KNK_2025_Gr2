package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MesuesiController {

    @FXML
    private void btnNxenesi(ActionEvent event) {
        loadFXML(event, "/views/nxenesiView.fxml");
    }

    @FXML
    private void btnNota(ActionEvent event) {
        loadFXML(event, "/views/MenaxhimiINotave.fxml");
    }

    @FXML
    private void btnStatistikat(ActionEvent event) {
        loadFXML(event, "/views/StatistikaView.fxml");
    }

    @FXML
    private void btnMungesat(ActionEvent event) {
        loadFXML(event, "/views/mungesa.fxml");
    }

    @FXML
    private void btnProfile(ActionEvent event) {
        loadFXML(event, "/views/ProfileView.fxml");
    }

    @FXML
    private void btnSignOut(ActionEvent event) {
        loadFXML(event, "/views/login.fxml");
    }

    private void loadFXML(ActionEvent event, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("❌ Nuk u gjet skedari: " + fxmlPath);
            e.printStackTrace();
        }
    }
}