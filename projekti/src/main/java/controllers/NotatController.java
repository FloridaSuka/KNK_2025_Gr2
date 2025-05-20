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

        } catch (NumberFormatException e) {
            System.out.println("Gabim në formatin e notave!");
            lblMesatarja.setText("Gabim në formatin e notave!");
            lblNotaFinale.setText("-");
        }
    }


    // Regjistron në ListView të gjitha notat
    @FXML
    private void regjistroNota() {
        String raport = "Nxënësi: " + txtEmriNxenesit.getText() +
                " | ID: " + txtIdNxenesit.getText() +
                " | Lënda: " + txtLenda.getText() +
                " | Mesuesi: " + txtEmriMesuesit.getText() +
                " | Id: " + txtIdMesuesit.getText() +
                " | Periudha: " + comboPeriudha.getValue() +
                " | Nota 1: " + nota1.getText() +
                " | Nota 2: " + nota2.getText() ;

        notat.add(raport);
    }

    // Pastron fushat dhe etiketat
    @FXML
    private void pastroFushat() {
        txtEmriNxenesit.clear();
        txtIdNxenesit.clear();
        txtLenda.clear();
        txtEmriMesuesit.clear();
        txtIdMesuesit.clear();
        comboPeriudha.getSelectionModel().clearSelection();
        nota1.clear();
        nota2.clear();

        lblMesatarja.setText("");
        lblNotaFinale.setText("");
    }
}
