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
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblError;

    @FXML
    private Hyperlink linkForgotPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private MenuButton menuLanguage;

    @FXML
    private MenuItem menuAL;

    @FXML
    private MenuItem menuEN;

    private ResourceBundle bundle;
    private Locale locale;

    private final UserService userService = new UserService();

   @FXML
public void initialize() {
    // Vendosim gjuhën fillestare në Shqip
    setLanguage("al");

    // Ndërrimi i gjuhës në Shqip
    menuAL.setOnAction(event -> setLanguage("al"));

    // Ndërrimi i gjuhës në Anglisht
    menuEN.setOnAction(event -> setLanguage("en"));
}


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

    private void setLanguage(String lang) {
    locale = new Locale(lang);
    bundle = ResourceBundle.getBundle("bundles.Messages", locale, getClass().getClassLoader());

    // Ndërro tekstet në mënyrë manuale
    menuLanguage.setText(bundle.getString("login.language"));
    txtUsername.setPromptText(bundle.getString("login.username"));
    txtPassword.setPromptText(bundle.getString("login.password"));
    linkForgotPassword.setText(bundle.getString("login.forgot_password"));
    btnLogin.setText(bundle.getString("login.button"));
    lblError.setText(""); // Pastrimi i gabimeve nëse ka

    // Ndryshim i skenës
    javafx.application.Platform.runLater(() -> {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"), bundle);
            Parent root = loader.load();

            Stage stage = (Stage) txtUsername.getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
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
            alert.setHeaderText("Fjalëkalimi u ndryshua me sukses!");
            alert.setContentText("Mund të kyçesh me fjalëkalimin e ri.");
            alert.showAndWait();
        });
    }
}