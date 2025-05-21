package controllers;

import database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Mungesa;
import repositories.*;
import utils.LanguageHandler;
import utils.SceneLocator;
import java.sql.Date;
import java.util.List;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import models.User.Role;
import javafx.application.Platform;
import services.UserService;
import javafx.stage.Stage;
import utils.MenuUtils;
import utils.SceneNavigator;


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
    @FXML private VBox root;
    @FXML private Menu menuOpen;
    @FXML private MenuItem menuCut, menuCopy, menuPaste, menuUndo, menuSelectAll, menuRedo;

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

        mbushTabelaMungesave();
        Platform.runLater(() -> {
            Role role = UserService.getCurrentUser().getRole();
            Stage stage = (Stage) root.getScene().getWindow();
            MenuUtils.populateOpenSubMenu(menuOpen, role, stage);
        });

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
        List<Mungesa> mungesat = mungesaRepo.gjejTeGjithaMungesat();
        for (Mungesa m : mungesat) {
            raportiMungesave.getItems().add(m.toString());
        }
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
