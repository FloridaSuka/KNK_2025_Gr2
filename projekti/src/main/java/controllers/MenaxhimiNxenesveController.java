package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import models.dto.create.CreateNxenesit;
import models.dto.update.UpdateNxenesit;
import services.NxenesitService;
import utils.LanguageHandler;
import utils.SceneLocator;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import models.User.Role;
import javafx.application.Platform;
import services.UserService;
import javafx.stage.Stage;
import utils.MenuUtils;
import utils.SceneNavigator;

public class MenaxhimiNxenesveController {
    @FXML
    private TextField txtId, txtEmri, txtMbiemri, txtDatelindja, txtGjinia, txtEmail, txtPhone, txtAdresa;
    private final NxenesitService service = new NxenesitService();
    @FXML private MenuButton menuLanguage;
    @FXML private VBox root;
    @FXML private Menu menuOpen;
    @FXML private MenuItem menuCut, menuCopy, menuPaste, menuUndo, menuSelectAll, menuRedo;

    @FXML public void initialize() {
        // Configure language menu
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.STUDENT_MANAGEMENT_PAGE);
        mbushRaportinENxenesve();

        Platform.runLater(() -> {
            Role role = UserService.getCurrentUser().getRole();
            Stage stage = (Stage) root.getScene().getWindow();
            MenuUtils.populateOpenSubMenu(menuOpen, role, stage);
        });
    }
    private void mbushRaportinENxenesve() {
    }

    @FXML
    private void shtoNxenes() {
        int adresaId = service.getAdresaId(txtAdresa.getText());
        CreateNxenesit nx = new CreateNxenesit(
                txtEmri.getText(),
                txtMbiemri.getText(),
                txtDatelindja.getText(),
                txtGjinia.getText().charAt(0),
                txtEmail.getText(),
                txtPhone.getText(),
                adresaId
        );

        if (service.shto(nx)) {
            showAlert("Sukses", "Nxënësi u shtua me sukses.");
        } else {
            showAlert("Gabim", "Nxënësi nuk u shtua.");
        }
    }

    @FXML
    private void fshijNxenes() {
        int id = Integer.parseInt(txtId.getText());
        if (service.fshij(id)) {
            showAlert("Sukses", "Nxënësi u fshi me sukses.");
        } else {
            showAlert("Gabim", "Nxënësi nuk u fshi.");
        }
    }

    @FXML
    private void perditesoNxenes() {
        int adresaId = service.getAdresaId(txtAdresa.getText());
        UpdateNxenesit nx = new UpdateNxenesit(
                Integer.parseInt(txtId.getText()),
                txtEmri.getText(),
                txtMbiemri.getText(),
                txtDatelindja.getText(),
                txtGjinia.getText().charAt(0),
                txtEmail.getText(),
                txtPhone.getText(),
                adresaId
        );

        if (service.perditeso(nx)) {
            showAlert("Sukses", "Nxënësi u përditësua me sukses.");
        } else {
            showAlert("Gabim", "Përditësimi dështoi.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML public void handleLogout(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        SceneNavigator.switchScene(stage, SceneLocator.LOGIN_PAGE);
    }
    @FXML public void handleSettings(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        SceneNavigator.switchScene(stage, SceneLocator.SETTINGS_PAGE);
    }
    @FXML public void handleHelp(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        SceneNavigator.switchScene(stage, SceneLocator.HELP_PAGE);
    }
    @FXML public void handleQuit(ActionEvent actionEvent) {
        Platform.exit();
    }
    @FXML public void handleNew(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        SceneNavigator.switchScene(stage, SceneLocator.ADD_USER_PAGE);
    }
    public void handleUndo(ActionEvent actionEvent) {
        MenuUtils.performUndo(menuUndo.getParentPopup().getOwnerWindow().getScene());
    }
    public void handleRedo(ActionEvent actionEvent) {
        MenuUtils.performRedo(menuRedo.getParentPopup().getOwnerWindow().getScene());
    }
    public void handleCut(ActionEvent actionEvent) {
        MenuUtils.performCut(menuCut.getParentPopup().getOwnerWindow().getScene());
    }
    public void handleCopy(ActionEvent actionEvent) {
        MenuUtils.performCopy(menuCopy.getParentPopup().getOwnerWindow().getScene());
    }
    public void handlePaste(ActionEvent actionEvent) {
        MenuUtils.performPaste(menuPaste.getParentPopup().getOwnerWindow().getScene());
    }

    public void handleSelectAll(ActionEvent actionEvent) {
        MenuUtils.performSelectAll(menuSelectAll.getParentPopup().getOwnerWindow().getScene());
    }
}
