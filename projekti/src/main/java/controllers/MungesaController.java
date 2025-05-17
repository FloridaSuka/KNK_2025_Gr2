package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import utils.LanguageHandler;
import utils.SceneLocator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MungesaController {

    @FXML
    private TextField txtKerkim, txtStudenti, txtLenda, txtKlasa, txtArsyeja;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ListView<String> listaMungesave;
    @FXML
    private Label lblTotalJave, lblTotalMuaj, lblNxenesiMeShumeMungesa, lblOra, lblData;
    @FXML
    private PieChart pieChart;
    @FXML private MenuButton menuLanguage;


    // Lista që do të përmbajë mungesat dhe statistikat
    private ObservableList<String> mungesat = FXCollections.observableArrayList();
    private Map<String, Integer> statistika = new HashMap<>();

    @FXML
    public void initialize() {
        // Orë dhe Datë që përditësohen automatikisht
        startClock();

        // Vendos listën e mungesave në ListView
        listaMungesave.setItems(mungesat);

        // Inicimi i PieChart
        pieChart.setTitle("Statistikat e Mungesave");

        //Zgjedhja e gjuhes
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.ABSENCES_PAGE);
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

            // Përditëso grafikun
            updateChart();

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

    // Metoda për të përditësuar orën dhe datën
    private void startClock() {
        Timer timer = new Timer();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    lblOra.setText("Ora: " + LocalTime.now().format(timeFormatter));
                    lblData.setText("Data: " + LocalDate.now().format(dateFormatter));
                });
            }
        }, 0, 1000);
    }

    // Metoda për përditësimin e grafikut
    private void updateChart() {
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> entry : statistika.entrySet()) {
            chartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        pieChart.setData(chartData);
    }

    // Kërkimi në ListView
    @FXML
    private void kerkoStudent(ActionEvent event) {
        String query = txtKerkim.getText().toLowerCase();
        ObservableList<String> filtruar = FXCollections.observableArrayList();

        for (String raport : mungesat) {
            if (raport.toLowerCase().contains(query)) {
                filtruar.add(raport);
            }
        }

        if (filtruar.isEmpty()) {
            listaMungesave.setItems(mungesat);
        } else {
            listaMungesave.setItems(filtruar);
        }
    }

    // Fshirja e një mungese
    @FXML
    private void fshiMungese(ActionEvent event) {
        String selectedItem = listaMungesave.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listaMungesave.getItems().remove(selectedItem);

            // Marrim emrin e nxënësit dhe e heqim nga statistikat
            String emri = selectedItem.split("\\|")[0].replace("Nxënësi:", "").trim();
            statistika.remove(emri);

            // Përditësojmë ListView dhe PieChart
            updateChart();
        } else {
            showError("Ju lutem zgjidhni një mungesë për ta fshirë.");
        }
    }
}
