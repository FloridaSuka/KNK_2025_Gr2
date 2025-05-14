package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.User;
import services.UserService;

import java.io.IOException;
import java.util.Optional;

public class LoginController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;
    @FXML private Hyperlink linkForgotPassword;
    @FXML private Button btnLogin;

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

    @FXML
    private void onForgotPassword() {
        // Hap një dialog për të vendosur fjalëkalimin e ri
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Rivendos Fjalëkalimin");
        dialog.setHeaderText("Vendos fjalëkalimin e ri:");
        dialog.setContentText("Fjalëkalimi i ri:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newPassword -> {
            // Këtu e ruajmë fjalëkalimin e ri
            // Për shembull mund të ruhet në databazë ose në fajll
            System.out.println("Fjalëkalimi u ndryshua në: " + newPassword);

            // Tregon një mesazh për konfirmim
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sukses");
            alert.setHeaderText("Fjalëkalimi u ndryshua me sukses!");
            alert.setContentText("Mund të kyçesh me fjalëkalimin e ri.");
            alert.showAndWait();
        });
    }
}