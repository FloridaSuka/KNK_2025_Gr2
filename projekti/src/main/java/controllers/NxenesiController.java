package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import models.dto.create.CreateNxenesit;
import models.dto.update.UpdateNxenesit;
import services.NxenesitService;
import utils.LanguageHandler;
import utils.SceneLocator;
import utils.SceneNavigator;

public class NxenesiController {




    @FXML private MenuButton menuLanguage;

    @FXML public void initialize() {
        // Configure language menu
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.STUDENT_PAGE);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        SceneNavigator.logout((Node) event.getSource());
    }

        @FXML private TextField txtId, txtEmri, txtMbiemri, txtDatelindja, txtGjinia, txtEmail, txtPhone, txtAdresa;
        private final NxenesitService service = new NxenesitService();

        @FXML
        private void shtoNxenes() {
            int adresaId = service.getAdresaId(txtAdresa.getText());
            CreateNxenesit nx = new CreateNxenesit(
                    txtEmri.getText(),
                    txtMbiemri.getText(),
                    txtDatelindja.getText(),
                    txtGjinia.getText().charAt(0),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    adresaId
            );

            if (service.shto(nx)) {
                showAlert("Sukses", "Nxënësi u shtua me sukses.");
            } else {
                showAlert("Gabim", "Nxënësi nuk u shtua.");
            }
        }

        @FXML
        private void fshijNxenes() {
            int id = Integer.parseInt(txtId.getText());
            if (service.fshij(id)) {
                showAlert("Sukses", "Nxënësi u fshi me sukses.");
            } else {
                showAlert("Gabim", "Nxënësi nuk u fshi.");
            }
        }

        @FXML
        private void perditesoNxenes() {
            int adresaId = service.getAdresaId(txtAdresa.getText());
            UpdateNxenesit nx = new UpdateNxenesit(
                    Integer.parseInt(txtId.getText()),
                    txtEmri.getText(),
                    txtMbiemri.getText(),
                    txtDatelindja.getText(),
                    txtGjinia.getText().charAt(0),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    adresaId
            );

            if (service.perditeso(nx)) {
                showAlert("Sukses", "Nxënësi u përditësua me sukses.");
            } else {
                showAlert("Gabim", "Përditësimi dështoi.");
            }
        }

        private void showAlert(String title, String content) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }
    private void onOpenStatistics(ActionEvent event) {
        SceneNavigator.switchScene((Node) event.getSource(), SceneLocator.NXENESIT_PAGE);
    }

}

