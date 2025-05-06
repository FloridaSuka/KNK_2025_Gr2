package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;
import services.UserService;

import java.io.IOException;

public class LoginController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;

    private final UserService userService = new UserService();

    @FXML
    private static final String ADMIN_FXML   = "/fxml/admin_dashboard.fxml";
    private static final String TEACHER_FXML = "/fxml/teacher_dashboard.fxml";

    @FXML
    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            lblError.setText("Plotëso të gjitha fushat");
            return;
        }

        User u = userService.authenticate(username, password);
        if (u == null) {
            lblError.setText("Kredenciale të pasakta");
            return;
        }

        try {
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            String target = (u.getRole() == User.Role.ADMIN) ? ADMIN_FXML : TEACHER_FXML;
            Parent root = FXMLLoader.load(getClass().getResource(target));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            lblError.setText("Nuk u hap faqja kryesore");
            ex.printStackTrace();
        }
    }
}
