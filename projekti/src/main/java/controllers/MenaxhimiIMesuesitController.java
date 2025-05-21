
package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.dto.create.CreateMesuesi;
import models.dto.update.UpdateMesuesi;
import repositories.AdresaRepository;
import services.MesuesiService;
import utils.LanguageHandler;
import utils.SceneLocator;

public class MenaxhimiIMesuesitController {
    @FXML private TextField txtId, txtEmri, txtMbiemri, txtEmail, txtTel, txtRoli, txtAdresa;
    @FXML private TableView<CreateMesuesi> tabelaMesuesit;
    @FXML private TableColumn<CreateMesuesi, String> kolEmri, kolMbiemri, kolEmail, kolTel, kolRoli, kolAdresa;

    private final MesuesiService mesuesiService = new MesuesiService();
    private final AdresaRepository adresaRepository = new AdresaRepository();
    @FXML
    private MenuButton menuLanguage;


    @FXML
    public void initialize() {
        kolEmri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().emri));
        kolMbiemri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().mbiemri));
        kolEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().email));
        kolTel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().tel));
        kolRoli.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().roli));
        kolAdresa.setCellValueFactory(cellData -> new SimpleStringProperty(txtAdresa.getText()));

        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.TEACHER_MANAGEMENT_PAGE);

    }

    @FXML
    private void shtoMesues() {
        String emriAdresa = txtAdresa.getText();
        Integer adresaId = adresaRepository.gjejAdresaId(emriAdresa);

        if (adresaId == null) {
            shfaqAlert("Gabim", "Adresa nuk u gjet!", "Adresa \"" + emriAdresa + "\" nuk ekziston në databazë.", Alert.AlertType.ERROR);
            return;
        }

        CreateMesuesi m = new CreateMesuesi(
                txtEmri.getText(), txtMbiemri.getText(), txtEmail.getText(),
                txtTel.getText(), txtRoli.getText(), adresaId
        );

        boolean success = mesuesiService.shto(m);
        if (success) {
            shfaqAlert("Sukses", "Mësuesi u shtua!", "Të dhënat janë ruajtur me sukses.", Alert.AlertType.INFORMATION);
            tabelaMesuesit.getItems().add(m);
        } else {
            shfaqAlert("Gabim", "Dështoi shtimi!", "Nuk u arrit të ruhet mësuesi në databazë.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void perditesoMesues() {
        if (txtId.getText().isEmpty()) {
            shfaqAlert("Gabim", "ID mungon!", "Ju lutem shkruani ID-n e mësuesit që doni të përditësoni.", Alert.AlertType.WARNING);
            return;
        }

        String emriAdresa = txtAdresa.getText();
        Integer adresaId = adresaRepository.gjejAdresaId(emriAdresa);
        if (adresaId == null) {
            shfaqAlert("Gabim", "Adresa nuk u gjet!", "Adresa \"" + emriAdresa + "\" nuk ekziston në databazë.", Alert.AlertType.ERROR);
            return;
        }

        UpdateMesuesi m = new UpdateMesuesi(
                Integer.parseInt(txtId.getText()), txtEmri.getText(), txtMbiemri.getText(),
                txtEmail.getText(), txtTel.getText(), txtRoli.getText(), adresaId
        );

        boolean success = mesuesiService.perditeso(m);
        if (success) {
            shfaqAlert("Sukses", "Përditësimi u krye!", "Të dhënat u përditësuan me sukses.", Alert.AlertType.INFORMATION);
        } else {
            shfaqAlert("Gabim", "Dështoi përditësimi!", "Nuk u arrit të përditësohen të dhënat në databazë.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void fshijMesues() {
        if (txtId.getText().isEmpty()) {
            shfaqAlert("Gabim", "ID mungon!", "Ju lutem shkruani ID-n e mësuesit që doni të fshini.", Alert.AlertType.WARNING);
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        boolean success = mesuesiService.fshij(id);
        if (success) {
            shfaqAlert("Sukses", "Fshirja u krye!", "Mësuesi me ID " + id + " u fshi me sukses.", Alert.AlertType.INFORMATION);
        } else {
            shfaqAlert("Gabim", "Dështoi fshirja!", "Nuk u arrit të fshihet mësuesi me ID " + id + ".", Alert.AlertType.ERROR);
        }
    }

    private void shfaqAlert(String titulli, String headeri, String mesazhi, Alert.AlertType tipi) {
        Alert alert = new Alert(tipi);
        alert.setTitle(titulli);
        alert.setHeaderText(headeri);
        alert.setContentText(mesazhi);
        alert.showAndWait();
    }
}
