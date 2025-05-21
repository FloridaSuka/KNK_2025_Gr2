package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.dto.create.CreateNxenesit;
import models.dto.update.UpdateNxenesit;
import services.NxenesitService;
import utils.LanguageHandler;
import utils.MenuUtils;
import utils.SceneLocator;

public class MenaxhimiNxenesveController {
    @FXML
    private TextField txtId, txtEmri, txtMbiemri, txtDatelindja, txtGjinia, txtEmail, txtPhone, txtAdresa;
    private final NxenesitService service = new NxenesitService();
    @FXML private MenuButton menuLanguage;

    @FXML public void initialize() {
        // Configure language menu
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.STUDENT_MANAGEMENT_PAGE);
        mbushRaportinENxenesve();
        String name = this.getClass().getSimpleName();
        System.out.println("🔍 Controller aktiv: " + name);
        MenuUtils.populateOpenSubMenu(menuOpen, name);
    }
    @FXML private MenuItem menuCut, menuCopy, menuPaste, menuUndo, menuSelectAll, menuRedo;
    @FXML private Menu menuOpen;

    @FXML
    public void handleNew(ActionEvent event) {
        MenuUtils.handleNew();
    }

    @FXML
    public void handleOpen() {
        // Shembull: ky controller është për admin
        MenuUtils.openConditionalView("MenaxhimiDrejtoreveController", "menaxhimiDrejtoreve.fxml", "Menaxhimi i Drejtoreve");
    }

    @FXML
    public void handleQuit() {
        System.exit(0);
    }

    @FXML
    public void handleUndo() {
        MenuUtils.performUndo(menuUndo.getParentPopup().getOwnerWindow().getScene());
    }

    @FXML
    public void handleRedo() {
        MenuUtils.performRedo(menuRedo.getParentPopup().getOwnerWindow().getScene());
    }

    @FXML
    public void handleCut() {
        MenuUtils.performCut(menuCut.getParentPopup().getOwnerWindow().getScene());
    }

    @FXML
    public void handleCopy() {
        MenuUtils.performCopy(menuCopy.getParentPopup().getOwnerWindow().getScene());
    }

    @FXML
    public void handlePaste() {
        MenuUtils.performPaste(menuPaste.getParentPopup().getOwnerWindow().getScene());
    }

    @FXML
    public void handleSelectAll() {
        MenuUtils.performSelectAll(menuSelectAll.getParentPopup().getOwnerWindow().getScene());
    }

    @FXML
    public void handleHelp() {
        MenuUtils.openhelp();
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
}
