package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import models.dto.create.CreateLenda;
import repositories.LendaRepository;
import utils.LanguageHandler;
import utils.SceneLocator;

public class LendaController {

    @FXML private TextField txtId;
    @FXML private TextField txtEmri;
    @FXML private TextField txtDrejtimi;
    @FXML private TextField txtPerioda;
    @FXML private TextField txtMesuesi;

    private final LendaRepository repo = new LendaRepository();
    @FXML
    private MenuButton menuLanguage;

    @FXML
    public void initialize() {
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.SUBJECT_MANAGEMENT_PAGE);
    }
    @FXML
    private void shtoLenda() {
        CreateLenda lenda = new CreateLenda(
                txtEmri.getText(),
                txtDrejtimi.getText(),
                txtPerioda.getText(),
                txtMesuesi.getText()
        );

        boolean success = repo.shto(lenda);
        showAlert(success, "Shtim", "Lënda u shtua me sukses!", "Shtimi dështoi!");
    }
    @FXML
    private void fshijLenda() {
        int id = Integer.parseInt(txtId.getText());
        boolean success = repo.fshij(id);
        showAlert(success, "Fshirje", "Lënda u fshi me sukses!", "Fshirja dështoi!");
    }

    private void showAlert(boolean success, String title, String msgSuccess, String msgFail) {
        Alert alert = new Alert(success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(success ? msgSuccess : msgFail);
        alert.showAndWait();
    }
}
