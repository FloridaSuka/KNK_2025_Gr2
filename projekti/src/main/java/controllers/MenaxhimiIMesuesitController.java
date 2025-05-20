package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.dto.create.CreateMesuesi;
import models.dto.update.UpdateMesuesi;
import repositories.AdresaRepository;
import services.MesuesiService;

import java.io.FileWriter;
import java.io.IOException;


public class MenaxhimiIMesuesitController {
    @FXML private TextField txtId, txtEmri, txtMbiemri, txtEmail, txtTel, txtRoli, txtAdresa;

    private final MesuesiService mesuesiService = new MesuesiService();
    private final AdresaRepository adresaRepository = new AdresaRepository();

    // ✅ Ruajtja në fajll
    private void ruajNeRaport(CreateMesuesi m) {
        String rruga = txtAdresa.getText();
        String data = String.format("Emri: %s, Mbiemri: %s, Email: %s, Tel: %s, Roli: %s, Adresa: %s%n",
                m.emri, m.mbiemri, m.email, m.tel, m.roli, rruga);
        try (FileWriter writer = new FileWriter("raporti_mesuesve.txt", true)) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ Shto mësues
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
            shfaqAlert("Sukses", "Mësuesi u shtua!", "Të dhënat janë ruajtur në databazë dhe raport.", Alert.AlertType.INFORMATION);
            ruajNeRaport(m);

        } else {
            shfaqAlert("Gabim", "Dështoi shtimi!", "Nuk u arrit të ruhet mësuesi në databazë.", Alert.AlertType.ERROR);
        }

    }

    // ✅ Përditëso mësues
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

    // ✅ Fshij mësues
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

    // ✅ Funksion ndihmës për të shfaqur alertat
    private void shfaqAlert(String titulli, String headeri, String mesazhi, Alert.AlertType tipi) {
        Alert alert = new Alert(tipi);
        alert.setTitle(titulli);
        alert.setHeaderText(headeri);
        alert.setContentText(mesazhi);
        alert.showAndWait();
    }
}
