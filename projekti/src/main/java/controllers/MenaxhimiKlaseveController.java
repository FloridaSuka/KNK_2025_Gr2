package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.dto.create.CreateKlasa;
import services.KlasaService;
import utils.LanguageHandler;
import utils.SceneLocator;

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
    }

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
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Klasa u shtua me sukses!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Gabim", "Klasa nuk u shtua!");
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String header) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

}
