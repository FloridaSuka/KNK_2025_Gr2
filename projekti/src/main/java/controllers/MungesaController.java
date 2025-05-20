package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Mungesa;
import repositories.*;

import java.sql.Date;

public class MungesaController {

    @FXML private TextField txtId;
    @FXML private TextField txtNxenesi;
    @FXML private TextField txtLenda;
    @FXML private TextField txtPerioda;
    @FXML private DatePicker txtData;
    @FXML private TextField txtArsyeja;

    private final MungesatRepository mungesaRepo = new MungesatRepository();
    private final NxenesitRepository nxenesiRepo = new NxenesitRepository();
    private final LendaRepository lendaRepo = new LendaRepository();
    private final PeriodaRepository periodaRepo = new PeriodaRepository();

    @FXML
    private void shtoMungese() {
        Integer studentId = nxenesiRepo.getNxenesiIdByName(txtNxenesi.getText().trim());
        Integer lendaId = lendaRepo.getLendaIdByName(txtLenda.getText().trim());
        Integer periodaId = periodaRepo.getPeriodaIdByName(txtPerioda.getText().trim());

        if (studentId == null || lendaId == null || periodaId == null || txtData.getValue() == null) {
            shfaqAlert("Gabim", "Të dhënat janë të paplota", "Sigurohuni që emrat ekzistojnë dhe data është zgjedhur.", Alert.AlertType.ERROR);
            return;
        }

        Mungesa m = new Mungesa(studentId, lendaId, periodaId, Date.valueOf(txtData.getValue()), txtArsyeja.getText());
        boolean success = mungesaRepo.perditeso(m.getId(), studentId, lendaId, periodaId, Date.valueOf(txtData.getValue()), txtArsyeja.getText());


        if (success) {
            shfaqAlert("Sukses", "Mungesa u shtua", "Të dhënat u ruajtën me sukses.", Alert.AlertType.INFORMATION);
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

        if (studentId == null || lendaId == null || periodaId == null || txtData.getValue() == null) {
            shfaqAlert("Gabim", "Të dhënat janë të paplota", "Sigurohuni që emrat ekzistojnë dhe data është zgjedhur.", Alert.AlertType.ERROR);
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        Mungesa m = new Mungesa(id, studentId, lendaId, periodaId, Date.valueOf(txtData.getValue()), txtArsyeja.getText());
        boolean success = mungesaRepo.perditeso(id, studentId, lendaId, periodaId, Date.valueOf(txtData.getValue()), txtArsyeja.getText());

        if (success) {
            shfaqAlert("Sukses", "U përditësua", "Të dhënat u përditësuan me sukses.", Alert.AlertType.INFORMATION);
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
            shfaqAlert("Sukses", "Fshirja u krye", "Mungesa u fshi me sukses.", Alert.AlertType.INFORMATION);
        } else {
            shfaqAlert("Gabim", "Dështoi fshirja", "Nuk u arrit të fshihet mungesa.", Alert.AlertType.ERROR);
        }
    }

    private void shfaqAlert(String titulli, String header, String mesazhi, Alert.AlertType tipi) {
        Alert alert = new Alert(tipi);
        alert.setTitle(titulli);
        alert.setHeaderText(header);
        alert.setContentText(mesazhi);
        alert.showAndWait();
    }
}
