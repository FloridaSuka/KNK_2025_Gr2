package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class NotatController {

    @FXML
    private TextField txtEmriNxenesit;

    @FXML
    private TextField txtLenda;

    @FXML
    private ComboBox<String> comboPeriudha;

    @FXML
    private TextField nota1;

    @FXML
    private TextField nota2;

    @FXML
    private TextField nota3;

    @FXML
    private Label lblMesatarja;

    @FXML
    private Label lblNotaFinale;

    @FXML
    private ListView<String> listaNotave;

    @FXML
    private Label lblDataOra1;

    @FXML
    private Label lblDataOra2;

    private ObservableList<String> notat = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listaNotave.setItems(notat);
        comboPeriudha.setItems(FXCollections.observableArrayList("1", "2"));
    }

    @FXML
    private void regjistroNota(ActionEvent actionEvent) {
        String emri = txtEmriNxenesit.getText();
        String lenda = txtLenda.getText();
        String periudha = comboPeriudha.getValue();
        String n1 = nota1.getText();
        String n2 = nota2.getText();
        String n3 = nota3.getText();

        if (!emri.isEmpty() && !lenda.isEmpty() && periudha != null && !n1.isEmpty() && !n2.isEmpty() && !n3.isEmpty()) {
            // Data dhe ora aktuale
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            // Krijimi i raportit me datë dhe orë
            String raport = "Nxënësi: " + emri + " | Lënda: " + lenda + " | Periudha: " + periudha +
                    " | Nota 1: " + n1 + " | Nota 2: " + n2 + " | Nota 3: " + n3 +
                    " | Regjistruar më: " + dtf.format(now);
            notat.add(raport);
            listaNotave.setItems(notat);

            // Vendosja në Label-a
            lblDataOra1.setText("Nota 1 dhe 2 u regjistruan më: " + dtf.format(now));
            lblDataOra2.setText("Nota 3 u regjistrua më: " + dtf.format(now));

            pastroFushat();
        } else {
            showError("Ju lutem plotësoni të gjitha fushat!");
        }
    }

    @FXML
    private void llogaritMesataren() {
        try {
            double n1 = Double.parseDouble(nota1.getText());
            double n2 = Double.parseDouble(nota2.getText());
            double n3 = Double.parseDouble(nota3.getText());

            double mesatarja = (n1 + n2 + n3) / 3;
            mesatarja = Math.round(mesatarja * 100.0) / 100.0;

            lblMesatarja.setText("Mesatarja: " + mesatarja);

            int notaFinale = (int) Math.round(mesatarja);
            lblNotaFinale.setText("Nota Finale: " + notaFinale);
        } catch (NumberFormatException e) {
            lblMesatarja.setText("Gabim në formatin e notave!");
            lblNotaFinale.setText("-");
        }
    }

    @FXML
    private void pastroFushat() {
        txtEmriNxenesit.clear();
        txtLenda.clear();
        nota1.clear();
        nota2.clear();
        nota3.clear();
        comboPeriudha.getSelectionModel().clearSelection();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
