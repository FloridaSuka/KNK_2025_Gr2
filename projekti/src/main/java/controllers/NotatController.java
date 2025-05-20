package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import utils.LanguageHandler;
import utils.SceneLocator;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private TextField txtIdNxenesit;

    @FXML
    private TextField txtIdMesuesit;

    @FXML
    private TextField txtEmriMesuesit;

    @FXML
    private Label lblMesatarja;

    @FXML
    private Label lblNotaFinale;
    @FXML private ComboBox<String> cmbDrejtimi;
    @FXML private ComboBox<String> cmbParalelja;
    @FXML private ComboBox<String> cmbKlasa;

    @FXML
    private ListView<String> listaNotave;

    private ObservableList<String> notat = FXCollections.observableArrayList();

    @FXML private MenuButton menuLanguage;

    @FXML
    public void initialize() {
        listaNotave.setItems(notat);
        comboPeriudha.setItems(FXCollections.observableArrayList("1", "2"));

        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.GRADE_MANAGEMENT_PAGE);

    }

    // Llogarit Mesataren dhe e shfaq atë
    @FXML
    private void llogaritMesataren() {
        System.out.println("Butoni u shtyp!");  // Test

        try {
            double n1 = Double.parseDouble(nota1.getText());
            double n2 = Double.parseDouble(nota2.getText());

            double mesatarja = (n1 + n2) / 2;
            mesatarja = Math.round(mesatarja * 100.0) / 100.0;

            System.out.println("Mesatarja e llogaritur: " + mesatarja);  // Test

            lblMesatarja.setText(String.valueOf(mesatarja)); // Vendoset direkt në Label
            int notaFinale = (int) Math.round(mesatarja);
            lblNotaFinale.setText(String.valueOf(notaFinale)); // Vendoset direkt në Label

        }catch (NumberFormatException e) {
            shfaqAlert("Gabim në Formatin e Notave", "Ju lutemi shkruani vetëm numra të vlefshëm për notat.");
            lblMesatarja.setText("Gabim");
            lblNotaFinale.setText("-");
        }

    }


    // Regjistron në ListView të gjitha notat
    @FXML
    private void regjistroNota() {
        String emriNxenesit = txtEmriNxenesit.getText();
        String idNxenesi = txtIdNxenesit.getText();
        String emriMesuesit = txtEmriMesuesit.getText();
        String idMesuesi = txtIdMesuesit.getText();
        String lenda = txtLenda.getText();
        String nota1Str = nota1.getText();
        String nota2Str = nota2.getText();
        String periudha = comboPeriudha.getValue();
        String drejtimi = cmbDrejtimi.getValue();
        String paralelja = cmbParalelja.getValue();
        String klasa = cmbKlasa.getValue();

        // Krijo përmbledhje si shembull (ose ruaji në DB)
        String raport = String.format("Nxënësi: %s (%s)\nMësuesi: %s (%s)\nLënda: %s\nNota1: %s | Nota2: %s\nPeriudha: %s\nDrejtimi: %s | Paralelja: %s | Klasa: %s",
                emriNxenesit, idNxenesi,
                emriMesuesit, idMesuesi,
                lenda, nota1Str, nota2Str,
                periudha, drejtimi, paralelja, klasa);

        listaNotave.getItems().add(raport);
    }


    // Pastron fushat dhe etiketat
    @FXML
    private void pastroFushat() {
        txtEmriNxenesit.clear();
        txtIdNxenesit.clear();
        txtEmriMesuesit.clear();
        txtIdMesuesit.clear();
        txtLenda.clear();
        nota1.clear();
        nota2.clear();
        comboPeriudha.getSelectionModel().clearSelection();
        cmbDrejtimi.getSelectionModel().clearSelection();
        cmbParalelja.getSelectionModel().clearSelection();
        cmbKlasa.getSelectionModel().clearSelection();
        lblMesatarja.setText("");
        lblNotaFinale.setText("");
    }

    private void shfaqAlert(String titulli, String mesazhi) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulli);
        alert.setHeaderText(null);
        alert.setContentText(mesazhi);
        alert.showAndWait();
    }

}
