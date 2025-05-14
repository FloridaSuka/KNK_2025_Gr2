package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MungesaController {

    @FXML
    private TextField txtKerkim, txtStudenti, txtLenda, txtKlasa, txtArsyeja;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ListView<String> listaStudentet, listaMungesave;
    @FXML
    private Label lblTotalJave, lblTotalMuaj, lblNxenesiMeShumeMungesa;

    // Lista që do të përmbajë studentët
    private ObservableList<String> studentet = FXCollections.observableArrayList("Arben Selimi", "Blerta Gashi", "Dion Bytyqi");
    private ObservableList<String> mungesat = FXCollections.observableArrayList();
    private Map<String, Integer> statistika = new HashMap<>();

    @FXML
    public void initialize() {
        // Vendos listën e studentëve në ListView
        listaStudentet.setItems(studentet);

        // Event Listener për klikimin në listë
        listaStudentet.setOnMouseClicked((MouseEvent event) -> {
            String emri = listaStudentet.getSelectionModel().getSelectedItem();
            if (emri != null) {
                txtStudenti.setText(emri);
            }
        });
    }

    @FXML
    private void kerkoStudent() {
        String query = txtKerkim.getText().toLowerCase();
        ObservableList<String> filtruar = FXCollections.observableArrayList();
        for (String student : studentet) {
            if (student.toLowerCase().contains(query)) {
                filtruar.add(student);
            }
        }
        listaStudentet.setItems(filtruar);
    }

    @FXML
    private void regjistroMungese(ActionEvent actionEvent) {
        String emri = txtStudenti.getText();
        String lenda = txtLenda.getText();
        String klasa = txtKlasa.getText();
        LocalDate data = datePicker.getValue();
        String arsyeja = txtArsyeja.getText();

        if (!emri.isEmpty() && data != null) {
            String raport = "Nxënësi: " + emri + " | Lënda: " + lenda + " | Klasa: " + klasa + " | Data: " + data + " | Arsyeja: " + arsyeja;
            mungesat.add(raport);
            listaMungesave.setItems(mungesat);

            // Përditëso statistikat
            statistika.put(emri, statistika.getOrDefault(emri, 0) + 1);
            lblTotalJave.setText("Total Mungesa (Javore): " + mungesat.size());
            lblTotalMuaj.setText("Total Mungesa (Mujore): " + mungesat.size());

            // Gjetja e nxënësit me më shumë mungesa
            String maxStudent = statistika.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("-");
            lblNxenesiMeShumeMungesa.setText("Nxënësi me më shumë mungesa: " + maxStudent);

            pastroFushat();
        } else {
            showError("Ju lutem plotësoni të gjitha fushat dhe datën!");
        }
    }

    @FXML
    private void pastroFushat() {
        txtStudenti.clear();
        txtLenda.clear();
        txtKlasa.clear();
        txtArsyeja.clear();
        datePicker.setValue(null);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void printoRaportin(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Printim i Raportit");
        alert.setHeaderText(null);
        alert.setContentText("Raporti i mungesave u printua me sukses!");
        alert.showAndWait();
    }

    @FXML
    public void eksportoExcel(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Eksportim në Excel");
        alert.setHeaderText(null);
        alert.setContentText("Lista e mungesave u eksportua me sukses në Excel!");
        alert.showAndWait();
    }

    public void shfaqGrafikun(ActionEvent actionEvent) {
    }
}
