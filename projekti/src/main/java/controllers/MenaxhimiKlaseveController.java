package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Klasa;
import models.dto.create.CreateKlasa;
import services.KlasaService;
import utils.LanguageHandler;
import utils.MenuUtils;
import utils.SceneLocator;

import java.util.List;

public class MenaxhimiKlaseveController {

    @FXML
    private TextField txtNiveli, txtShkolla ,txtParalelja, txtProfesori, txtDrejtimi, txtId;



    @FXML
    private Button btnShto;

    private final KlasaService klasaService = new KlasaService();

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
    @FXML private TableView<Klasa> tabelaKlasave;
    @FXML private TableColumn<Klasa, Integer> colId;
    @FXML private TableColumn<Klasa, Integer> colNiveli;
    @FXML private TableColumn<Klasa, String> colShkolla;
    @FXML private TableColumn<Klasa, String> colParalelja;
    @FXML private TableColumn<Klasa, String> colMesuesi;
    @FXML private TableColumn<Klasa, String> colDrejtimi;


    @FXML
    private void shtoKlasa() {
        int shkollaId = klasaService.lookupId("shkolla", "emri", txtShkolla.getText());
        int paraleljaId = klasaService.lookupId("paralelja", "emri", txtParalelja.getText());
        int profesoriId = klasaService.lookupId("mesuesi", "emri", txtProfesori.getText());
        int drejtimiId = klasaService.lookupId("drejtimi", "emri", txtDrejtimi.getText());

        CreateKlasa klasa = new CreateKlasa(
                Integer.parseInt(txtNiveli.getText()),
                shkollaId, paraleljaId, profesoriId, drejtimiId
        );

        if (klasaService.shtoKlasa(klasa)) {




            mbushTabelen();

        } else {


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
