package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Mungesa;
import models.dto.create.CreateMungesa;
import models.dto.update.UpdateMungesa;

import java.net.URL;
import java.util.ResourceBundle;

public class MungesaController implements Initializable {

    @FXML private TableView<Mungesa> tableMungesat;
    @FXML private TableColumn<Mungesa, Integer> colId;
    @FXML private TableColumn<Mungesa, String> colStudent;
    @FXML private TableColumn<Mungesa, Integer> colLenda;
    @FXML private TableColumn<Mungesa, Integer> colPerioda;
    @FXML private TableColumn<Mungesa, String> colData;
    @FXML private TableColumn<Mungesa, String> colArsyeja;

    @FXML private TextField txtId, txtStudent, txtLenda, txtPerioda, txtData, txtArsyeja;

    private ObservableList<Mungesa> mungesat = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStudent.setCellValueFactory(new PropertyValueFactory<>("student"));
        colLenda.setCellValueFactory(new PropertyValueFactory<>("lendaId"));
        colPerioda.setCellValueFactory(new PropertyValueFactory<>("periodaId"));
        colData.setCellValueFactory(new PropertyValueFactory<>("dataMungeses"));
        colArsyeja.setCellValueFactory(new PropertyValueFactory<>("arsyeja"));
        tableMungesat.setItems(mungesat);
    }

    @FXML
    private void shtoMungese() {
        try {
            var dto = new CreateMungesa(
                    txtStudent.getText(),
                    Integer.parseInt(txtLenda.getText()),
                    Integer.parseInt(txtPerioda.getText()),
                    txtData.getText(),
                    txtArsyeja.getText()
            );
            int id = mungesat.size() + 1;
            var m = Mungesa.of(id, dto.getStudent(), dto.getLendaId(), dto.getPeriodaId(), dto.getDataMungeses(), dto.getArsyeja());
            mungesat.add(m);
            pastro();
        } catch (Exception e) {
            alert("Gabim", e.getMessage());
        }
    }

    @FXML
    private void perditesoMungese() {
        try {
            int id = Integer.parseInt(txtId.getText());
            var dto = new UpdateMungesa(
                    id,
                    txtStudent.getText(),
                    Integer.parseInt(txtLenda.getText()),
                    Integer.parseInt(txtPerioda.getText()),
                    txtData.getText(),
                    txtArsyeja.getText()
            );
            for (int i = 0; i < mungesat.size(); i++) {
                if (mungesat.get(i).getId() == id) {
                    mungesat.set(i, Mungesa.of(dto.getId(), dto.getStudent(), dto.getLendaId(), dto.getPeriodaId(), dto.getDataMungeses(), dto.getArsyeja()));
                    break;
                }
            }
            pastro();
        } catch (Exception e) {
            alert("Gabim", e.getMessage());
        }
    }

    @FXML
    private void fshijMungese() {
        try {
            int id = Integer.parseInt(txtId.getText());
            mungesat.removeIf(m -> m.getId() == id);
            pastro();
        } catch (Exception e) {
            alert("Gabim", e.getMessage());
        }
    }

    private void pastro() {
        txtId.clear(); txtStudent.clear(); txtLenda.clear(); txtPerioda.clear(); txtData.clear(); txtArsyeja.clear();
    }

    private void alert(String title, String msg) {
        var a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
