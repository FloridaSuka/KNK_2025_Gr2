package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.User;
import services.UserService;
import utils.LanguageHandler;
import utils.SceneLocator;
import javafx.scene.control.Button;
import utils.SceneNavigator;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Hyperlink linkForgotPassword;
    private final UserService userService = new UserService();
    @FXML private MenuButton menuLanguage;

    @FXML public void initialize() {
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.LOGIN_PAGE);
    }
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

            txtUsername.clear();
            txtPassword.clear();
            txtUsername.requestFocus();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kredenciale të pasakta");
            alert.setHeaderText("Kyçja dështoi!");
            alert.setContentText("Përdoruesi ose fjalëkalimi janë të pasakta. Provo përsëri.");
            alert.showAndWait();
            return;
        }

        try {
            Node source = txtUsername;

            switch (u.getRole()) {
                case ADMIN:
                    System.out.println("🔄 Duke kaluar në Admin View...");
                    SceneNavigator.switchScene(source, "/views/AdminView.fxml");
                    break;

                case PRINCIPAL:
                case DREJTOR:
                    System.out.println("🔄 Duke kaluar në Drejtor View...");
                    SceneNavigator.switchScene(source, "/views/DrejtorView.fxml");
                    break;

                case MESUES:
                case TEACHER:
                    System.out.println("🔄 Duke kaluar në Teacher View...");
                    SceneNavigator.switchScene(source, "/views/TeacherView.fxml");
                    break;

                case STUDENT:
                case NXENES:
                    System.out.println("🔄 Duke kaluar në Student View...");
                    SceneNavigator.switchScene(source, "/views/StudentView.fxml");
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
    private void onForgotPassword(ActionEvent event) {
        try {
            // 🔹 Ngarkojmë burimet (ResourceBundle)
            Locale locale = Locale.getDefault();
            ResourceBundle bundle = ResourceBundle.getBundle("bundles.Messages", locale);

            // 🔹 Ngarkojmë view-in duke përdorur path-in e centralizuar nga SceneLocator
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/rivendosFjalekalimin.fxml"), bundle);

            // 🔹 Ngarkojmë pamjen (view-in)
            Parent root = loader.load();

            // 🔹 Marrim skenën aktuale dhe vendosim pamjen e re
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Rivendos Fjalëkalimin");
            stage.show();

            System.out.println("✅ U ridrejtua në RivendosFjalekalimin.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Gabim gjatë ngarkimit të RivendosFjalekalimin.fxml");
        }
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