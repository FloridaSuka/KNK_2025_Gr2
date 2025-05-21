package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Klasa;
import models.dto.create.CreateKlasa;
import repositories.KlasaRepository;
import services.KlasaService;
import utils.LanguageHandler;
import utils.SceneLocator;

import java.util.List;

public class MenaxhimiKlaseveController {

    @FXML
    private TextField txtNiveli, txtShkolla ,txtParalelja, txtProfesori, txtDrejtimi, txtId;

    private final KlasaService klasaService = new KlasaService();
    private final KlasaRepository repo= new KlasaRepository();

    @FXML
    private MenuButton menuLanguage;

    @FXML
    public void initialize() {
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.CLASS_MANAGEMENT_PAGE);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNiveli.setCellValueFactory(new PropertyValueFactory<>("niveli"));
        colShkolla.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShkolla().getEmri()));
        colParalelja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getParalelja().getEmri()));
        colMesuesi.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getMesuesi().getEmri() + " " + cellData.getValue().getMesuesi().getMbiemri()));
        colDrejtimi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDrejtimi().getEmri()));
        mbushTabelen();
    }
    @FXML private TableView<Klasa> tabelaKlasave;
    @FXML private TableColumn<Klasa, Integer> colId;
    @FXML private TableColumn<Klasa, Integer> colNiveli;
    @FXML private TableColumn<Klasa, String> colShkolla;
    @FXML private TableColumn<Klasa, String> colParalelja;
    @FXML private TableColumn<Klasa, String> colMesuesi;
    @FXML private TableColumn<Klasa, String> colDrejtimi;


    @FXML
    private void shtoKlasa() {
        try {
            if (txtNiveli.getText().isEmpty() || txtShkolla.getText().isEmpty() || txtParalelja.getText().isEmpty()
                    || txtProfesori.getText().isEmpty() || txtDrejtimi.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Vërejtje", "Ju lutem plotësoni të gjitha fushat.");
                return;
            }

            int niveli = Integer.parseInt(txtNiveli.getText());
            int shkollaId = klasaService.lookupId("shkolla", "emri", txtShkolla.getText());
            int paraleljaId = klasaService.lookupId("paralelja", "emri", txtParalelja.getText());
            int profesoriId = klasaService.lookupId("mesuesi", "emri", txtProfesori.getText());
            int drejtimiId = klasaService.lookupId("drejtimi", "emri", txtDrejtimi.getText());

            if (shkollaId == -1 || paraleljaId == -1 || profesoriId == -1 || drejtimiId == -1) {
                showAlert(Alert.AlertType.ERROR, "Gabim", "Një nga entitetet nuk u gjet në databazë.");
                return;
            }

            CreateKlasa klasa = new CreateKlasa(niveli, shkollaId, paraleljaId, profesoriId, drejtimiId);
            boolean success = klasaService.shtoKlasa(klasa);
            if (success) {
                mbushTabelen();
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Klasa u shtua me sukses.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Gabim", "Nuk u arrit të ruhet klasa.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Gabim", "Fusha 'Niveli' duhet të jetë numër.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Gabim", "Ndodhi një gabim gjatë ruajtjes: " + e.getMessage());
        }
    }

    @FXML
    private void fshijKlasa() {
        int id = Integer.parseInt(txtId.getText());
        try {
            System.out.println("📌 ID për fshirje: " + id);
            boolean success = repo.fshij(id);
            showAlert(Alert.AlertType.INFORMATION,"Sukses" ,"Fshirja u krye!");
            if (success) mbushTabelen();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR,"Gabim", "Fshirja dështoi!");
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String header) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    private void mbushTabelen() {
        List<Klasa> klasat = klasaService.gjejTeGjitha();
        ObservableList<Klasa> listaObservable = FXCollections.observableArrayList(klasat);
        tabelaKlasave.setItems(listaObservable);
    }


}
