package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Perioda;
import models.dto.create.CreatePeriodaDTO;
import models.dto.update.UpdatePeriodaDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class PeriodaController implements Initializable {

    @FXML private TableView<Perioda> tablePerioda;
    @FXML private TableColumn<Perioda, Integer> colId;
    @FXML private TableColumn<Perioda, String> colEmri;
    @FXML private TableColumn<Perioda, String> colFillimi;
    @FXML private TableColumn<Perioda, String> colMbarimi;

    @FXML private TextField txtId, txtEmri, txtFillimi, txtMbarimi;

    private ObservableList<Perioda> periodat = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("idPerioda"));
        colEmri.setCellValueFactory(new PropertyValueFactory<>("emri"));
        colFillimi.setCellValueFactory(new PropertyValueFactory<>("dataFillimit"));
        colMbarimi.setCellValueFactory(new PropertyValueFactory<>("dataMbarimit"));

        tablePerioda.setItems(periodat);
    }

    @FXML
    private void shtoPerioda() {
        var dto = new CreatePeriodaDTO(
                txtEmri.getText(),
                txtFillimi.getText(),
                txtMbarimi.getText()
        );

        int id = periodat.size() + 1;
        Perioda p = new Perioda(id, dto.getEmri(), dto.getDataFillimit(), dto.getDataMbarimit());
        periodat.add(p);
        pastroFushat();
    }

    @FXML
    private void perditesoPerioda() {
        int id = Integer.parseInt(txtId.getText());
        var dto = new UpdatePeriodaDTO(
                id,
                txtEmri.getText(),
                txtFillimi.getText(),
                txtMbarimi.getText()
        );

        for (int i = 0; i < periodat.size(); i++) {
            if (periodat.get(i).getIdPerioda() == id) {
                periodat.set(i, new Perioda(dto.getIdPerioda(), dto.getEmri(), dto.getDataFillimit(), dto.getDataMbarimit()));
                break;
            }
        }
        pastroFushat();
    }

    @FXML
    private void fshijPerioda() {
        int id = Integer.parseInt(txtId.getText());
        periodat.removeIf(p -> p.getIdPerioda() == id);
        pastroFushat();
    }

    private void pastroFushat() {
        txtId.clear(); txtEmri.clear(); txtFillimi.clear(); txtMbarimi.clear();
    }
}
