package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.dto.create.CreateNotat;
import services.NotatService;

public class NotatController {

    @FXML private TextField txtIdNxenesit, txtIdMesuesit, txtLenda, nota1, nota2;
    @FXML private ComboBox<String> cmbKlasa, cmbParalelja, cmbDrejtimi;
    @FXML private Label lblMesatarja, lblNotaFinale;
    @FXML private ListView<String> listaNotave;

    private final NotatService notatService = new NotatService();

    @FXML
    private void regjistroNota() {
        // Kontrollo nëse ndonjë fushë është bosh
        if (txtIdNxenesit.getText().isEmpty() || txtIdMesuesit.getText().isEmpty() ||
                txtLenda.getText().isEmpty() || nota1.getText().isEmpty() || nota2.getText().isEmpty() ||
                cmbDrejtimi.getValue() == null || cmbParalelja.getValue() == null || cmbKlasa.getValue() == null) {
            showAlert("Gabim", "Ju lutem plotësoni të gjitha fushat!");
            return;
        }

        try {
            int nxenesiId = Integer.parseInt(txtIdNxenesit.getText().trim());
            int mesuesiId = Integer.parseInt(txtIdMesuesit.getText().trim());
            int lendaId = Integer.parseInt(txtLenda.getText().trim());
            int notaPare = Integer.parseInt(nota1.getText().trim());
            int notaDyte = Integer.parseInt(nota2.getText().trim());

            int drejtimiId = convertDrejtimiToId(cmbDrejtimi.getValue());
            int paraleljaId = convertParaleljaToId(cmbParalelja.getValue());
            int klasaId = Integer.parseInt(cmbKlasa.getValue().trim());

            if (drejtimiId == 0 || paraleljaId == 0 || klasaId == 0) {
                showAlert("Gabim", "Vlerat e drejtimit, paraleles apo klasës janë të pavlefshme.");
                return;
            }

            // ✅ Krijimi i objektit për ruajtje
            CreateNotat nota = new CreateNotat(nxenesiId, lendaId, mesuesiId, drejtimiId, klasaId, paraleljaId, notaPare, notaDyte);
            boolean sukses = notatService.regjistroNota(nota);

            if (sukses) {
                showAlert("Sukses!", "Nota u ruajt me sukses!");
                shtoNeRaport(); // shton notën në raportin ListView
                pastroFushat();
            } else {
                showAlert("Gabim!", "Dështoi ruajtja e notës.");
            }

        } catch (NumberFormatException e) {
            showAlert("Gabim në format", "Sigurohu që ID-të dhe notat janë numra të vlefshëm.");
        }
    }

    private void shtoNeRaport() {
        String raport = "Nxënësi ID: " + txtIdNxenesit.getText() +
                " | Lënda ID: " + txtLenda.getText() +
                " | Nota 1: " + nota1.getText() +
                " | Nota 2: " + nota2.getText();
        listaNotave.getItems().add(raport);
    }

    @FXML
    private void llogaritMesataren() {
        try {
            double n1 = Double.parseDouble(nota1.getText().trim());
            double n2 = Double.parseDouble(nota2.getText().trim());
            double mesatarja = (n1 + n2) / 2.0;

            lblMesatarja.setText(String.format("%.2f", mesatarja));

            String notaFinale;
            if (mesatarja >= 9) notaFinale = "10";
            else if (mesatarja >= 8) notaFinale = "9";
            else if (mesatarja >= 7) notaFinale = "8";
            else if (mesatarja >= 6) notaFinale = "7";
            else if (mesatarja >= 5) notaFinale = "6";
            else notaFinale = "5";

            lblNotaFinale.setText(notaFinale);

        } catch (NumberFormatException e) {
            showAlert("Gabim", "Ju lutem shkruani nota valide për të llogaritur mesataren.");
        }
    }

    @FXML
    private void pastroFushat() {
        txtIdNxenesit.clear();
        txtIdMesuesit.clear();
        txtLenda.clear();
        nota1.clear();
        nota2.clear();
        cmbDrejtimi.setValue(null);
        cmbParalelja.setValue(null);
        cmbKlasa.setValue(null);
        lblMesatarja.setText("");
        lblNotaFinale.setText("");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private int convertDrejtimiToId(String drejtimi) {
        return switch (drejtimi) {
            case "Shoqëror" -> 1;
            case "Natyror" -> 2;
            case "Ekonomik" -> 3;
            case "Teknik" -> 4;
            default -> 0;
        };
    }

    private int convertParaleljaToId(String paralelja) {
        return switch (paralelja) {
            case "A" -> 1;
            case "B" -> 2;
            case "C" -> 3;
            case "D" -> 4;
            case "E" -> 5;
            case "F" -> 6;
            default -> 0;
        };
    }
}
