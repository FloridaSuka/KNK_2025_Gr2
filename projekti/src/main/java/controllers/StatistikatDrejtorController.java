package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import repositories.MungesatRepository;
import repositories.NotatRepository;

public class StatistikatDrejtorController {

    @FXML
    private PieChart outerPieChart;  // Për notat

    @FXML
    private PieChart innerPieChart;  // Për mungesat

    @FXML
    private Button btnRifresko;

    private final NotatRepository notatRepo = new NotatRepository();
    private final MungesatRepository repo = new MungesatRepository();

    @FXML
    public void initialize() {
        ngarkoGrafiket();
        btnRifresko.setOnAction(e -> ngarkoGrafiket());
    }

    private void ngarkoGrafiket() {
        // ------------------------- NOTAT -------------------------
        ObservableList<PieChart.Data> notat = FXCollections.observableArrayList(
                new PieChart.Data("Nota 1", notatRepo.numronNotat(1)),
                new PieChart.Data("Nota 2", notatRepo.numronNotat(2)),
                new PieChart.Data("Nota 3", notatRepo.numronNotat(3)),
                new PieChart.Data("Nota 4", notatRepo.numronNotat(4)),
                new PieChart.Data("Nota 5", notatRepo.numronNotat(5))
        );
        outerPieChart.setData(notat);
        outerPieChart.setLabelsVisible(true);
        outerPieChart.setLegendVisible(false);

        // ------------------------- MUNGESAT me NORMALIZIM -------------------------
        ObservableList<PieChart.Data> mungesat = FXCollections.observableArrayList(
                new PieChart.Data("Meshkuj", repo.numriMungesavePerGjinine("M")),
                new PieChart.Data("Femra", repo.numriMungesavePerGjinine("F"))
        );

        ObservableList<PieChart.Data> mungesatNormalizuara = normalizo(mungesat);
        innerPieChart.setData(mungesatNormalizuara);
        innerPieChart.setLabelsVisible(true);
        innerPieChart.setLegendVisible(false);

        // ------------------------- NGJYRAT + TOOLTIP -------------------------
        Platform.runLater(() -> {
            setPieColors(notat, new String[]{"#2980b9", "#27ae60", "#f1c40f", "#e67e22", "#c0392b"});
            setPieColors(mungesatNormalizuara, new String[]{"#1abc9c", "#e74c3c"});

            vendosTooltip(notat);
            vendosTooltip(mungesatNormalizuara);
        });
    }

    private ObservableList<PieChart.Data> normalizo(ObservableList<PieChart.Data> lista) {
        double total = lista.stream().mapToDouble(PieChart.Data::getPieValue).sum();
        ObservableList<PieChart.Data> normalizuar = FXCollections.observableArrayList();
        for (PieChart.Data d : lista) {
            double vleraNormale = (d.getPieValue() / total) * 100;
            normalizuar.add(new PieChart.Data(d.getName(), vleraNormale));
        }
        return normalizuar;
    }

    private void setPieColors(ObservableList<PieChart.Data> dataList, String[] colors) {
        for (int i = 0; i < dataList.size(); i++) {
            final PieChart.Data data = dataList.get(i);
            final String color = colors[i % colors.length];
            if (data.getNode() != null) {
                data.getNode().setStyle("-fx-pie-color: " + color + ";");
            }
        }
    }

    private void vendosTooltip(ObservableList<PieChart.Data> dataList) {
        for (PieChart.Data data : dataList) {
            Tooltip tooltip = new Tooltip(
                    data.getName() + ": " + String.format("%.2f", data.getPieValue()) + "%"
            );
            Tooltip.install(data.getNode(), tooltip);
        }
    }
}

