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

    @FXML private TableView<Lenda> tableLenda;
    @FXML private TableColumn<Lenda, Integer> colIdLenda;
    @FXML private TableColumn<Lenda, String> colEmri;
    @FXML private TableColumn<Lenda, Integer> colIdMesimi;
    @FXML private TableColumn<Lenda, Integer> colIdDrejtimi;
    @FXML private TableColumn<Lenda, Integer> colIdPerioda;
    @FXML private TableColumn<Lenda, Integer> colIdMesuesi;

    @FXML private TextField txtId, txtEmri, txtMesimi, txtDrejtimi, txtPerioda, txtMesuesi;

    private ObservableList<Lenda> lendet = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colIdLenda.setCellValueFactory(new PropertyValueFactory<>("idLenda"));
        colEmri.setCellValueFactory(new PropertyValueFactory<>("emri"));
        colIdMesimi.setCellValueFactory(new PropertyValueFactory<>("idMesimi"));
        colIdDrejtimi.setCellValueFactory(new PropertyValueFactory<>("idDrejtimi"));
        colIdPerioda.setCellValueFactory(new PropertyValueFactory<>("idPerioda"));
        colIdMesuesi.setCellValueFactory(new PropertyValueFactory<>("idMesuesi"));

        tableLenda.setItems(lendet);
    }

    @FXML
    private void shtoLende() {
        var dto = new CreateLenda(
                txtEmri.getText(),
                Integer.parseInt(txtMesimi.getText()),
                Integer.parseInt(txtDrejtimi.getText()),
                Integer.parseInt(txtPerioda.getText()),
                Integer.parseInt(txtMesuesi.getText())
        );

        int id = lendet.size() + 1;
        Lenda l = new Lenda(id, dto.getEmri(), dto.getIdMesimi(), dto.getIdDrejtimi(), dto.getIdPerioda(), dto.getIdMesuesi());
        lendet.add(l);
        pastroFushat();
    }

    @FXML
    private void perditesoLende() {
        int id = Integer.parseInt(txtId.getText());
        var dto = new UpdateLenda(
                id,
                txtEmri.getText(),
                Integer.parseInt(txtMesimi.getText()),
                Integer.parseInt(txtDrejtimi.getText()),
                Integer.parseInt(txtPerioda.getText()),
                Integer.parseInt(txtMesuesi.getText())
        );

        for (int i = 0; i < lendet.size(); i++) {
            if (lendet.get(i).getIdLenda() == id) {
                lendet.set(i, new Lenda(dto.getIdLenda(), dto.getEmri(), dto.getIdMesimi(), dto.getIdDrejtimi(), dto.getIdPerioda(), dto.getIdMesuesi()));
                break;
            }
        }
        pastroFushat();
    }

    @FXML
    private void fshijLende() {
        int id = Integer.parseInt(txtId.getText());
        lendet.removeIf(l -> l.getIdLenda() == id);
        pastroFushat();
    }

    private void pastroFushat() {
        txtId.clear(); txtEmri.clear(); txtMesimi.clear(); txtDrejtimi.clear(); txtPerioda.clear(); txtMesuesi.clear();
    }
}
