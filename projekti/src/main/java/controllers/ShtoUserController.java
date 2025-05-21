package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.User;
import models.dto.create.CreateUser;
import services.UserService;
import utils.LanguageHandler;
import utils.SceneLocator;

public class ShtoUserController {

    @FXML
    private TextField txtEmri;
    @FXML
    private TextField txtMbiemri;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPerdoruesi;
    @FXML
    private PasswordField txtFjalekalimi;
    @FXML
    private PasswordField txtFjalekalimi2;
    @FXML
    private RadioButton radioDrejtor;
    @FXML
    private RadioButton radioMesues;
    @FXML
    private RadioButton radioNxenes;

    private final ToggleGroup roleGroup = new ToggleGroup();
    private final UserService userService = new UserService();

    @FXML
    private MenuButton menuLanguage;

    @FXML
    public void initialize() {
        radioDrejtor.setToggleGroup(roleGroup);
        radioMesues.setToggleGroup(roleGroup);
        radioNxenes.setToggleGroup(roleGroup);
        roleGroup.selectToggle(null);

        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.ADD_USER_PAGE);
    }


    @FXML
    private void handleRegister() {
        if (!validateInputs()) return;

        String emri = txtEmri.getText();
        String mbiemri = txtMbiemri.getText();
        String email = txtEmail.getText();
        String perdoruesi = txtPerdoruesi.getText();
        String fjalekalimi = txtFjalekalimi.getText();
        String roli = ((RadioButton) roleGroup.getSelectedToggle()).getText();

        // Mapimi i roleve saktë
        User.Role role = switch (roli) {
            case "Principal" -> User.Role.PRINCIPAL;
            case "Teacher" -> User.Role.TEACHER;
            case "Student" -> User.Role.STUDENT;
            case "Drejtor" -> User.Role.DREJTOR;
            default -> User.Role.ADMIN;
        };

        CreateUser user = new CreateUser(perdoruesi, fjalekalimi, email, emri, mbiemri, role);

        boolean uShtua = userService.register(user);

        if (uShtua) {
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Përdoruesi u regjistrua me sukses!");
            clearFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "Dështim", "Përdoruesi nuk u regjistrua!");
        }
    }


    private boolean validateInputs() {
        if (txtEmri.getText().isEmpty() || txtMbiemri.getText().isEmpty() ||
                txtEmail.getText().isEmpty() || txtPerdoruesi.getText().isEmpty() ||
                txtFjalekalimi.getText().isEmpty() || txtFjalekalimi2.getText().isEmpty() ||
                roleGroup.getSelectedToggle() == null) {
            showAlert(Alert.AlertType.ERROR, "Gabim", "Të dhëna të paplotësuara");
            return false;
        }

        if (!txtFjalekalimi.getText().equals(txtFjalekalimi2.getText())) {
            showAlert(Alert.AlertType.ERROR, "Gabim", "Fjalëkalimet nuk përputhen");
            return false;
        }

        return true;
    }

    private void showAlert(Alert.AlertType alertType, String title, String header) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    private void clearFields() {
        txtEmri.clear();
        txtMbiemri.clear();
        txtEmail.clear();
        txtPerdoruesi.clear();
        txtFjalekalimi.clear();
        txtFjalekalimi2.clear();
        roleGroup.selectToggle(null);
    }
}
