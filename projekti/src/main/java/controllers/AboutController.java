package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AboutController {

    @FXML
    private void closeWindow(ActionEvent event) {
        // Merr Stage-in aktual nga butoni
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
