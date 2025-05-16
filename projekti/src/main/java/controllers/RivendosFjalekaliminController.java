package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RivendosFjalekaliminController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtNewPassword;
    @FXML private PasswordField txtConfirmPassword;

    @FXML
    void handlePasswordChange(ActionEvent event) {
        String username = txtUsername.getText().trim();
        String newPassword = txtNewPassword.getText().trim();
        String confirmPassword = txtConfirmPassword.getText().trim();

        if (username.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Të gjitha fushat duhet të plotësohen!");
            alert.show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fjalëkalimet nuk përputhen!");
            alert.show();
            return;
        }

        // Këtu mund të thërrasësh metodën `updatePassword` për të bërë update në databazë
        System.out.println("Fjalëkalimi i përdoruesit " + username + " u ndryshua me sukses.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Fjalëkalimi u ndryshua me sukses!");
        alert.show();
    }
}
