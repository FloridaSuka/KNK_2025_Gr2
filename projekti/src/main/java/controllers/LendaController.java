package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Lenda;
import models.dto.create.CreateLenda;
import models.dto.update.UpdateLenda;

import java.net.URL;
import java.util.ResourceBundle;

public class LendaController implements Initializable {

    @FXML private TableView<Lenda> tableLendet;
    @FXML private TableColumn<Lenda, Integer> colId;
    @FXML private TableColumn<Lenda, String> colEmri;
    @FXML private TableColumn<Lenda, Integer> colMesimi;
    @FXML private TableColumn<Lenda, Integer> colDrejtimi;
    @FXML private TableColumn<Lenda, Integer> colPerioda;
    @FXML private TableColumn<Lenda, Integer> colMesuesi;

    @FXML private TextField txtId, txtEmri, txtMesimi, txtDrejtimi, txtPerioda, txtMesuesi;

    private ObservableList<Lenda> lendet = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("idLenda"));
        colEmri.setCellValueFactory(new PropertyValueFactory<>("emri"));
        colMesimi.setCellValueFactory(new PropertyValueFactory<>("idMesimi"));
        colDrejtimi.setCellValueFactory(new PropertyValueFactory<>("idDrejtimi"));
        colPerioda.setCellValueFactory(new PropertyValueFactory<>("idPerioda"));
        colMesuesi.setCellValueFactory(new PropertyValueFactory<>("idMesuesi"));

        tableLendet.setItems(lendet);
    }

    @FXML
    private void shtoLende() {
<<<<<<< Updated upstream
        var dto = new CreateLenda(
                txtEmri.getText(),
                Integer.parseInt(txtMesimi.getText()),
                Integer.parseInt(txtDrejtimi.getText()),
                Integer.parseInt(txtPerioda.getText()),
                Integer.parseInt(txtMesuesi.getText())
        );
=======
        try {
            CreateLendaDTO dto = new CreateLendaDTO(
                    txtEmri.getText(),
                    Integer.parseInt(txtMesimi.getText()),
                    Integer.parseInt(txtDrejtimi.getText()),
                    Integer.parseInt(txtPerioda.getText()),
                    Integer.parseInt(txtMesuesi.getText())
            );
>>>>>>> Stashed changes

            int id = lendet.size() + 1;
            Lenda lenda = Lenda.of(id, dto.getEmri(), dto.getIdMesimi(), dto.getIdDrejtimi(), dto.getIdPerioda(), dto.getIdMesuesi());
            lendet.add(lenda);
            pastroFushat();
        } catch (Exception e) {
            showAlert("Gabim", "Gabim gjatë shtimit: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void perditesoLende() {
<<<<<<< Updated upstream
        int id = Integer.parseInt(txtId.getText());
        var dto = new UpdateLenda(
                id,
                txtEmri.getText(),
                Integer.parseInt(txtMesimi.getText()),
                Integer.parseInt(txtDrejtimi.getText()),
                Integer.parseInt(txtPerioda.getText()),
                Integer.parseInt(txtMesuesi.getText())
        );
=======
        try {
            int id = Integer.parseInt(txtId.getText());
            UpdateLendaDTO dto = new UpdateLendaDTO(
                    id,
                    txtEmri.getText(),
                    Integer.parseInt(txtMesimi.getText()),
                    Integer.parseInt(txtDrejtimi.getText()),
                    Integer.parseInt(txtPerioda.getText()),
                    Integer.parseInt(txtMesuesi.getText())
            );
>>>>>>> Stashed changes

            for (int i = 0; i < lendet.size(); i++) {
                if (lendet.get(i).getIdLenda() == id) {
                    Lenda eRe = Lenda.of(dto.getIdLenda(), dto.getEmri(), dto.getIdMesimi(), dto.getIdDrejtimi(), dto.getIdPerioda(), dto.getIdMesuesi());
                    lendet.set(i, eRe);
                    break;
                }
            }
            pastroFushat();
        } catch (Exception e) {
            showAlert("Gabim", "Gabim gjatë përditësimit: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void fshijLende() {
        try {
            int id = Integer.parseInt(txtId.getText());
            lendet.removeIf(l -> l.getIdLenda() == id);
            pastroFushat();
        } catch (Exception e) {
            showAlert("Gabim", "Gabim gjatë fshirjes: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void pastroFushat() {
        txtId.clear(); txtEmri.clear(); txtMesimi.clear(); txtDrejtimi.clear(); txtPerioda.clear(); txtMesuesi.clear();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
