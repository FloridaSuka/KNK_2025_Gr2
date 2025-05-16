package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NxenesiController {

    @FXML
    private TextField txtEmri;

    @FXML
    private TextField txtMbiemri;

    @FXML
    private TextField txtID;

    @FXML
    private ComboBox<String> comboKlasa;

    @FXML
    private ListView<String> listaNxenesve;

    private ObservableList<String> nxenesit = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listaNxenesve.setItems(nxenesit);
        comboKlasa.setItems(FXCollections.observableArrayList("10", "11", "12"));
    }

    @FXML
    private void regjistroNxenesin() {
        String emri = txtEmri.getText();
        String mbiemri = txtMbiemri.getText();
        String id = txtID.getText();
        String klasa = comboKlasa.getValue();

        if (emri.isEmpty() || mbiemri.isEmpty() || id.isEmpty() || klasa == null) {
            showAlert("Të gjitha fushat janë të detyrueshme!");
            return;
        }

        String info = "ID: " + id +
                " | Emri: " + emri +
                " " + mbiemri +
                " | Klasa: " + klasa;

        nxenesit.add(info);
        pastroFushat();
    }

    @FXML
    private void fshijNxenesin() {
        String selected = listaNxenesve.getSelectionModel().getSelectedItem();
        if (selected != null) {
            nxenesit.remove(selected);
        } else {
            showAlert("Zgjidh një nxënës për ta fshirë.");
        }
    }

    @FXML
    private void pastroFushat() {
        txtEmri.clear();
        txtMbiemri.clear();
        txtID.clear();
        comboKlasa.getSelectionModel().clearSelection();
    }

    private void showAlert(String mesazhi) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Paralajmërim");
        alert.setHeaderText(null);
        alert.setContentText(mesazhi);
        alert.showAndWait();
    }
}

