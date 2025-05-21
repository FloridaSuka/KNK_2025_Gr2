package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Drejtor;
import models.Klasa;
import models.dto.create.CreateDrejtor;
import models.dto.update.UpdateDrejtor;
import models.dto.update.UpdateShkolla;
import repositories.AdresaRepository;
import repositories.DrejtoriRepository;
import services.DrejtorService;
import utils.LanguageHandler;
import utils.MenuUtils;
import utils.SceneLocator;

import java.util.List;

import static utils.ZipUtils.gjejQytetinNgaZip;

public class MenaxhimiDrejtoreveController {
    @FXML private TextField txtAdresa;
    @FXML private TextField txtzip;
    @FXML private TextField txtEmri;
    @FXML private TextField txtMbiemri;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTel;
    @FXML private TextField txtIdShkolla;
    @FXML private TextField txtId;
    @FXML private TableView<Drejtor> tabelaDrejtorit;
    @FXML private TableColumn<Drejtor, Integer> colId;
    @FXML private TableColumn<Drejtor, String> colEmri, colMbiemri,colShkolla,colTel;

    private final DrejtoriRepository drejtoriRepo = new DrejtoriRepository();
    private final AdresaRepository adresaRepo = new AdresaRepository();
    private final DrejtorService service = new DrejtorService();
    @FXML
    private MenuButton menuLanguage;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmri.setCellValueFactory(new PropertyValueFactory<>("emri"));
        colMbiemri.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colShkolla.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShkollaId().getEmri()));
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.PRINCIPAL_MANAGEMENT_PAGE);
        mbushTabelen();
    }


    @FXML
    private void shtoDrejtor() {
        String rruga = txtAdresa.getText();
        String kodiPostar = txtzip.getText();
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

        CreateDrejtor d = new CreateDrejtor(
                txtEmri.getText(),
                txtMbiemri.getText(),
                txtEmail.getText(),
                txtTel.getText(),
                adresaId,
                Integer.parseInt(txtIdShkolla.getText())
        );

        DrejtoriRepository drejtoriRepo = new DrejtoriRepository();
        boolean success = drejtoriRepo.shtoDrejtor(d);
        if(success)
            mbushTabelen();
        showAlert(success, "Shtim", "Drejtori u shtua me sukses!", "Shtimi dështoi.");
    }
    @FXML
    private void perditesoDrejtor() {
        int id = Integer.parseInt(txtId.getText());
        String rruga = txtAdresa.getText();
        String zip = txtzip.getText();
        Integer adresaId = 0;
        if (!rruga.isBlank() && !zip.isBlank()) {
            String qyteti = gjejQytetinNgaZip(zip);
            if (qyteti != null) {
                adresaId = adresaRepo.shtoAdrese(qyteti, rruga, zip);
            }
        }

        UpdateDrejtor d = new UpdateDrejtor(
                id,
                txtEmri.getText().isBlank() ? null : txtEmri.getText(),
                txtMbiemri.getText().isBlank() ? null : txtMbiemri.getText(),
                txtEmail.getText().isBlank() ? null : txtEmail.getText(),
                txtTel.getText().isBlank() ? null : txtTel.getText(),
                adresaId,
                txtIdShkolla.getText().isBlank() ? 0 : Integer.parseInt(txtIdShkolla.getText())
        );


        boolean success = drejtoriRepo.perditesoDrejtor(d);
        if(success)
            mbushTabelen();
        showAlert(success, "Përditësim", "Drejtori u përditësua me sukses!", "Përditësimi dështoi.");
    }

    @FXML
    private void fshijDrejtor() {
        int id = Integer.parseInt(txtId.getText());
        boolean success = drejtoriRepo.fshijDrejtor(id);
        if(success)
            mbushTabelen();
        showAlert(success, "Fshirje", "Drejtori u fshi me sukses!", "Fshirja dështoi.");
    }
    private void showAlert(boolean success, String title, String msgSuccess, String msgFail) {
        Alert alert = new Alert(success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(success ? msgSuccess : msgFail);
        alert.showAndWait();
    }

    private void mbushTabelen() {
        List<Drejtor> drejtoret = service.gjejTeGjithe();
        ObservableList<Drejtor> listaObservable = FXCollections.observableArrayList(drejtoret);
        tabelaDrejtorit.setItems(listaObservable);
    }
}
