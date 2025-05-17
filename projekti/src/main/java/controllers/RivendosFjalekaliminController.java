package controllers;

import database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.UserService;
import utils.LanguageHandler;
import utils.SceneLocator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class RivendosFjalekaliminController {

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private PasswordField txtPassword1;

    @FXML
    private PasswordField txtPassword2;

    @FXML
    private Button btnAlbanian;

    @FXML
    private Button btnEnglish;

    @FXML
    private MenuButton menuLanguage;

    @FXML public void initialize() {
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.RESET_PASSWORD);
    }

    @FXML
    void handleChange(ActionEvent event) {
        // ✅ Marrim të dhënat nga fushat në formë
        String username = txtUsername.getText().trim();
        String oldPassword = txtPassword.getText().trim();
        String newPassword = txtPassword1.getText().trim();
        String confirmPassword = txtPassword2.getText().trim();

        if (username.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Kujdes!", "Fusha për emrin e përdoruesit është e zbrazët!");
            txtUsername.requestFocus();
            return;
        }

        if (oldPassword.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Kujdes!", "Fusha për fjalëkalimin e vjetër është e zbrazët!");
            txtPassword.requestFocus();
            return;
        }

        if (newPassword.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Kujdes!", "Fusha për fjalëkalimin e ri është e zbrazët!");
            txtPassword1.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Kujdes!", "Fusha për konfirmimin e fjalëkalimit është e zbrazët!");
            txtPassword2.requestFocus();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Gabim!", "Fjalëkalimet nuk përputhen!");
            txtPassword1.clear();
            txtPassword2.clear();
            txtPassword1.requestFocus();
            return;
        }

        if (!newPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            showAlert(Alert.AlertType.ERROR, "Fjalëkalimi i pavlefshëm",
                    "Fjalëkalimi duhet të përmbajë:\n- Të paktën një shkronjë të madhe\n- Të paktën një shkronjë të vogël\n- Të paktën një numër\n- Të jetë të paktën 8 karaktere.");
            txtPassword1.clear();
            txtPassword2.clear();
            txtPassword1.requestFocus();
            return;
        }

        // ✅ 2. Krijojmë instancën e `UserService`
        UserService userService = new UserService();

        // ✅ 3. Kontrollo në databazë nëse ekziston username me password-in e vjetër
        if (userService.verifyUser(username, oldPassword)) {
            // ✅ Nëse ekziston, bëjmë update në databazë
            if (userService.updatePassword(username, newPassword)) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses!", "Fjalëkalimi u ndryshua me sukses.");

                // 🚀 **Opsionale**: Pas suksesit, ridrejtohet te Login
                Stage stage = (Stage) txtUsername.getScene().getWindow();
                SceneLocator.locate(stage, SceneLocator.LOGIN_PAGE);

            } else {
                showAlert(Alert.AlertType.ERROR, "Gabim!", "Nuk u arrit të ndryshohet fjalëkalimi.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Gabim!", "Përdoruesi ose fjalëkalimi i vjetër është gabim.");
        }
    }

    // ✅ Metoda për shfaqje mesazhesh
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void handleEnglishLanguage(ActionEvent event) {
        Locale.setDefault(new Locale("en"));

        Stage stage = (Stage) menuLanguage.getScene().getWindow();
        SceneLocator.locate(stage, SceneLocator.LOGIN_PAGE);
    }

    @FXML
    void handleAlbanianLanguage(ActionEvent event) {
        Locale.setDefault(new Locale("al"));

        Stage stage = (Stage) menuLanguage.getScene().getWindow();
        SceneLocator.locate(stage, SceneLocator.LOGIN_PAGE);
    }
}
