package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.dto.create.CreateShkolla;
import models.dto.update.UpdateShkolla;
import repositories.BaseRepository;
import repositories.ShkollaRepository;

public class MenaxhimiShkollesController {

    @FXML private TextField txtId;
    @FXML private TextField txtEmri;
    @FXML private TextField txtTel;
    @FXML private TextField txtAdresa;

    private final ShkollaRepository repo = new ShkollaRepository();
    private final BaseRepository base= new BaseRepository();

    @FXML
    private void shto() {
        int adresaId = base.gjejId(txtAdresa.getText(), "Adresa");
        CreateShkolla shkolla = new CreateShkolla(
                txtEmri.getText(),
                txtTel.getText(),
                adresaId
        );
        boolean success = repo.shtoShkollen(shkolla);
        showAlert(success, "Shtim", "Shkolla u shtua me sukses!", "Shtimi dështoi.");
    }

    @FXML
    private void perditeso() {
        int adresaId = base.gjejId(txtAdresa.getText(), "Adresa");
        UpdateShkolla shkolla = new UpdateShkolla(
                Integer.parseInt(txtId.getText()),
                txtEmri.getText(),
                txtTel.getText(),
                adresaId
        );
        boolean success = repo.perditesoShkollen(shkolla);
        showAlert(success, "Përditësim", "Shkolla u përditësua me sukses!", "Përditësimi dështoi.");
    }

    @FXML
    private void fshij() {
        int id = Integer.parseInt(txtId.getText());
        boolean success = repo.fshijShkollen(id);
        showAlert(success, "Fshirje", "Shkolla u fshi me sukses!", "Fshirja dështoi.");
    }

    private void showAlert(boolean success, String title, String msgSuccess, String msgFail) {
        Alert alert = new Alert(success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(success ? msgSuccess : msgFail);
        alert.showAndWait();
    }
}