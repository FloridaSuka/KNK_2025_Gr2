package controllers;

import database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Mungesa;
import repositories.*;
import services.MungesaService;
import utils.LanguageHandler;
import utils.MenuUtils;
import utils.SceneLocator;
import utils.SceneNavigator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class MungesaController {

    @FXML private TextField txtId;
    @FXML private TextField txtNxenesi;
    @FXML private TextField txtLenda;
    @FXML private TextField txtPerioda;
    @FXML private TextField txtArsyeja;

    private final MungesatRepository mungesaRepo = new MungesatRepository();
    private final NxenesitRepository nxenesiRepo = new NxenesitRepository();
    private final LendaRepository lendaRepo = new LendaRepository();
    private final PeriodaRepository periodaRepo = new PeriodaRepository();
    @FXML private TableView<Mungesa> tabelaMungesat;
    @FXML private TableColumn<Mungesa, Integer> colId;
    @FXML private TableColumn<Mungesa, Integer> colStudentId;
    @FXML private TableColumn<Mungesa, Integer> colLendaId;
    @FXML private TableColumn<Mungesa, Integer> colPeriodaId;
    @FXML private TableColumn<Mungesa, Date> colData;
    @FXML private TableColumn<Mungesa, String> colArsyeja;

    @FXML
    private Label lblStatus;

    @FXML private ListView<String> raportiMungesave;

    @FXML
    private MenuButton menuLanguage;

    @FXML
    public void initialize() {
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.ABSENCES_PAGE);
        mbushRaportinMungesave();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colLendaId.setCellValueFactory(new PropertyValueFactory<>("lendaId"));
        colPeriodaId.setCellValueFactory(new PropertyValueFactory<>("periodaId"));
        colData.setCellValueFactory(new PropertyValueFactory<>("dataMungeses"));
        colArsyeja.setCellValueFactory(new PropertyValueFactory<>("arsyeja"));

        mbushTabelaMungesave(); // e thirr metoda e mbushjes
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

    private void mbushTabelaMungesave() {

            List<Mungesa> lista = mungesaRepo.gjejTeGjithaMungesat();
            tabelaMungesat.getItems().clear();
            tabelaMungesat.getItems().addAll(lista);
        }



    @FXML
    private void shtoMungese() {
        Integer studentId = nxenesiRepo.getNxenesiIdByName(txtNxenesi.getText().trim());
        Integer lendaId = lendaRepo.getLendaIdByName(txtLenda.getText().trim());
        Integer periodaId = periodaRepo.getPeriodaIdByName(txtPerioda.getText().trim());

        if (studentId == null || lendaId == null || periodaId == null) {
            shfaqAlert("Gabim", "ID nuk u gjet", "Kontrolloni emrat e futur.", Alert.AlertType.ERROR);
            return;
        }

        Date data = new Date(System.currentTimeMillis()); // përdor datën aktuale

        Mungesa m = new Mungesa(studentId, lendaId, periodaId, data, txtArsyeja.getText());

        boolean success = mungesaRepo.shto(studentId, lendaId, periodaId, data, txtArsyeja.getText());

        if (success) {
            shfaqAlert("Sukses", "Mungesa u ruajt", "Të dhënat u ruajtën me sukses.", Alert.AlertType.INFORMATION);
            mbushTabelaMungesave();

        } else {
            shfaqAlert("Gabim", "Dështoi ruajtja", "Nuk u arrit të ruhet mungesa.", Alert.AlertType.ERROR);
        }
    }
    @FXML
    private void perditesoMungese() {
        if (txtId.getText().isEmpty()) {
            shfaqAlert("Gabim", "ID mungon", "Ju lutem shkruani ID për të përditësuar.", Alert.AlertType.WARNING);
            return;
        }

        Integer studentId = nxenesiRepo.getNxenesiIdByName(txtNxenesi.getText().trim());
        Integer lendaId = lendaRepo.getLendaIdByName(txtLenda.getText().trim());
        Integer periodaId = periodaRepo.getPeriodaIdByName(txtPerioda.getText().trim());

        if (studentId == null || lendaId == null || periodaId == null) {
            lblStatus.setText("Dështoi përditësimi i mungesës.");
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        Mungesa m = new Mungesa(id, studentId, lendaId, periodaId, new Date(System.currentTimeMillis()), txtArsyeja.getText());
        boolean success = mungesaRepo.perditeso(id, studentId, lendaId, periodaId, new Date(System.currentTimeMillis()), txtArsyeja.getText());

        if (success) {
            shfaqAlert("Sukses", "U përditësua", "Të dhënat u përditësuan me sukses.", Alert.AlertType.INFORMATION);
            mbushTabelaMungesave();

        } else {
            shfaqAlert("Gabim", "Dështoi përditësimi", "Nuk u arrit të përditësohen të dhënat.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void fshijMungese() {
        if (txtId.getText().isEmpty()) {
            shfaqAlert("Gabim", "ID mungon", "Ju lutem shkruani ID për të fshirë.", Alert.AlertType.WARNING);
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        boolean success = mungesaRepo.fshij(id);

        if (success) {
            lblStatus.setText("Mungesa u ruajt me sukses më: " + java.time.LocalDateTime.now());
            mbushTabelaMungesave();

        } else {
            lblStatus.setText(" Dështoi ruajtja e mungesës.");
        }
    }

    private void shfaqAlert(String titulli, String header, String mesazhi, Alert.AlertType tipi) {
        Alert alert = new Alert(tipi);
        alert.setTitle(titulli);
        alert.setHeaderText(header);
        alert.setContentText(mesazhi);
        alert.showAndWait();
    }
    private void mbushRaportinMungesave() {
        raportiMungesave.getItems().clear();
        List<Mungesa> mungesat = mungesaRepo.gjejTeGjithaMungesat(); // duhet ta shtosh këtë metodë në repository
        for (Mungesa m : mungesat) {
            raportiMungesave.getItems().add(m.toString());
        }
    }




}
