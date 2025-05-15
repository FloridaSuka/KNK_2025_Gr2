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
import utils.SceneLocator;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

public class LoginController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Hyperlink linkForgotPassword;

    @FXML private Button btnAlbanian;
    @FXML private Button btnEnglish;
    @FXML private MenuButton menuLanguage;

    private final UserService userService = new UserService();

    @FXML
    private static final String ADMIN_FXML   = "/fxml/admin_dashboard.fxml";
    private static final String TEACHER_FXML = "/fxml/teacher_dashboard.fxml";

    @FXML
    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kredenciale të Zbrazëta");
            alert.setHeaderText("Ju lutem plotësoni të gjitha fushat!");
            alert.setContentText("Përdoruesi dhe Fjalëkalimi nuk mund të jenë të zbrazëta.");
            alert.showAndWait();
            return;
        }

        User u = userService.authenticate(username, password);

        if (u == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kredenciale të pasakta");
            alert.setHeaderText("Kyçja dështoi!");
            alert.setContentText("Përdoruesi ose fjalëkalimi janë të pasakta. Provo përsëri.");
            alert.showAndWait();
            return;
        }

        try {
            Stage stage = (Stage) txtUsername.getScene().getWindow();

            switch (u.getRole()) {
                case ADMIN:
                    SceneLocator.locate(stage, SceneLocator.ADMIN_PAGE);
                    break;
                case DREJTOR:
                    SceneLocator.locate(stage, SceneLocator.PRINCIPAL_PAGE);
                    break;
                default:
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Roli i panjohur");
                    alert.setHeaderText("Roli i përdoruesit nuk është i njohur!");
                    alert.setContentText("Kontrolloni të dhënat e përdoruesit ose kontaktoni administratorin.");
                    alert.showAndWait();
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim gjatë hapjes së faqes");
            alert.setHeaderText("Ndodhi një gabim gjatë ngarkimit të pamjes kryesore.");
            alert.setContentText("Kontrolloni lidhjen dhe provoni përsëri.");
            alert.showAndWait();
            ex.printStackTrace();
        }
    }




    @FXML
    private void onForgotPassword() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Rivendos Fjalëkalimin");
        dialog.setHeaderText("Vendos fjalëkalimin e ri:");
        dialog.setContentText("Fjalëkalimi i ri:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newPassword -> {
            System.out.println("Fjalëkalimi u ndryshua në: " + newPassword);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sukses");
            alert.setHeaderText("Fjalëkalimi u ndryshua me sukses!");
            alert.setContentText("Mund të kyçesh me fjalëkalimin e ri.");
            alert.showAndWait();
        });
    }


    @FXML
    void handleEnglishLanguage(ActionEvent event) {
        Locale.setDefault(new Locale("en"));

        Stage stage = (Stage) menuLanguage.getScene().getWindow();
        SceneLocator.locate(stage, SceneLocator.LOGIN_PAGE);
    }

    @FXML
    void handleAlbanianLanguage(ActionEvent event) {
        Locale.setDefault(new Locale("sq"));

        Stage stage = (Stage) menuLanguage.getScene().getWindow();
        SceneLocator.locate(stage, SceneLocator.LOGIN_PAGE);
    }

}