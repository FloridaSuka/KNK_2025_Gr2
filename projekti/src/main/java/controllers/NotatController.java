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
    private TextField txtMesuesi;

    @FXML
    private Label lblMesatarja;

    @FXML
    private Label lblNotaFinale;

    @FXML
    private Label lblDataOra1;

    @FXML
    private Label lblDataOra2;


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

    // Regjistrimi i datës kur vendoset Nota 1
    @FXML
    private void onKeyReleasedNota1(KeyEvent event) {
        if (!nota1.getText().isEmpty()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            lblDataOra1.setText("Vendosur më: " + dtf.format(LocalDateTime.now()));
        }
    }

    // Regjistrimi i datës kur vendoset Nota 2
    @FXML
    private void onKeyReleasedNota2(KeyEvent event) {
        if (!nota2.getText().isEmpty()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            lblDataOra2.setText("Vendosur më: " + dtf.format(LocalDateTime.now()));
        }
    }


    // Llogarit Mesataren dhe e shfaq atë
    @FXML
    private void llogaritMesataren() {
        try {
            double n1 = Double.parseDouble(nota1.getText());
            double n2 = Double.parseDouble(nota2.getText());

            double mesatarja = (n1 + n2 ) / 2;
            mesatarja = Math.round(mesatarja * 100.0) / 100.0;

            lblMesatarja.setText("Mesatarja: " + mesatarja);

            int notaFinale = (int) Math.round(mesatarja);
            lblNotaFinale.setText("Nota Finale: " + notaFinale);

        } catch (NumberFormatException e) {
            lblMesatarja.setText("Gabim në formatin e notave!");
            lblNotaFinale.setText("-");
        }
    }

    // Regjistron në ListView të gjitha notat
    @FXML
    private void regjistroNota() {
        String raport = "Nxënësi: " + txtEmriNxenesit.getText() +
                " | Lënda: " + txtLenda.getText() +
                " | Mesuesi: " + txtMesuesi.getText() +
                " | Periudha: " + comboPeriudha.getValue() +
                " | Nota 1: " + nota1.getText() +
                " | Nota 2: " + nota2.getText() ;

        notat.add(raport);
    }

    // Pastron fushat dhe etiketat
    @FXML
    private void pastroFushat() {
        txtEmriNxenesit.clear();
        txtLenda.clear();
        txtMesuesi.clear();
        comboPeriudha.getSelectionModel().clearSelection();
        nota1.clear();
        nota2.clear();

        lblDataOra1.setText("");
        lblDataOra2.setText("");

        lblMesatarja.setText("");
        lblNotaFinale.setText("");
    }
}
