package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Shkolla;
import models.dto.create.CreateShkolla;
import models.dto.update.UpdateShkolla;
import repositories.AdresaRepository;
import repositories.ShkollaRepository;
import utils.LanguageHandler;
import utils.MenuUtils;
import utils.SceneLocator;

import static utils.ZipUtils.gjejQytetinNgaZip;
import java.util.List;

public class MenaxhimiShkollesController {

    @FXML private TextField txtId;
    @FXML private TextField txtEmri;
    @FXML private TextField txtTel;
    @FXML private TextField txtAdresa;
    @FXML private TextField txtZip;
    @FXML
    private ListView<String> raportiShkollave;

    private final ShkollaRepository repo = new ShkollaRepository();
    private final AdresaRepository adresaRepo = new AdresaRepository();
    @FXML
    private MenuButton menuLanguage;

    @FXML
    public void initialize() {
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.ADMIN_PAGE);
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
    @FXML
    private void shto() {
        String rruga = txtAdresa.getText();
        String kodiPostar = txtZip.getText();
        String qyteti = gjejQytetinNgaZip(kodiPostar);

        if (qyteti == null) {
            showAlert(false, "Gabim", "Kodi postar i panjohur!", "Kontrolloni kodin postar.");
            return;
        }

        AdresaRepository adresaRepo = new AdresaRepository();
        Integer adresaId = adresaRepo.shtoAdrese(qyteti, rruga, kodiPostar);

        if (adresaId == null) {
            showAlert(false, "Gabim", "Adresa nuk u ruajt!", "Adresa dështoi.");
            return;
        }
        CreateShkolla shkolla=new CreateShkolla(
                txtEmri.getText(),
                txtTel.getText(),
                adresaId
        );

        ShkollaRepository shkollarepo=new ShkollaRepository();
        boolean success = shkollarepo.shtoShkollen(shkolla);


        showAlert(success, "Shtim", "Shkolla u shtua me sukses!", "Shtimi dështoi.");
    }

    @FXML
    private void perditeso() {
        int id = Integer.parseInt(txtId.getText());
        String rruga = txtAdresa.getText();
        String zip = txtZip.getText();
        Integer adresaId = 0;
        if (!rruga.isBlank() && !zip.isBlank()) {
            String qyteti = gjejQytetinNgaZip(zip);
            if (qyteti != null) {
                adresaId = adresaRepo.shtoAdrese(qyteti, rruga, zip);
            }
        }
        UpdateShkolla shkolla = new UpdateShkolla(
                id,
                txtEmri.getText().isBlank() ? null : txtEmri.getText(),
                txtTel.getText().isBlank() ? null : txtTel.getText(),
                adresaId
        );

        boolean success = repo.perditesoShkollen(shkolla);
        showAlert(success, "Përditësim", "Shkolla u përditësua me sukses!", "Përditësimi dështoi.");
    }

    @FXML
    private void fshij() {
        int id = Integer.parseInt(txtId.getText());
        boolean success = repo.fshijShkollen(id);

        if (success) {
            raportiShkollave.getItems().add(" Fshirje: ID " + id + " | Emri: " + txtEmri.getText());
        }

    }

    private void showAlert(boolean success, String title, String msgSuccess, String msgFail) {
        Alert alert = new Alert(success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(success ? msgSuccess : msgFail);
        alert.showAndWait();
    }

}