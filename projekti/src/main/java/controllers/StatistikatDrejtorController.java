package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class StatistikatDrejtorController {
    @FXML
    private PieChart outerPieChart;

    @FXML
    private PieChart innerPieChart;

    @FXML
    public void initialize() {
        // Outer chart
        ObservableList<PieChart.Data> outerData = FXCollections.observableArrayList(
                new PieChart.Data("Nota 5", 40),
                new PieChart.Data("Nota 4", 30),
                new PieChart.Data("Nota 3", 20),
                new PieChart.Data("Nota 2", 10)
        );
        outerPieChart.setData(outerData);
        outerPieChart.setLabelsVisible(true);
        outerPieChart.setLegendVisible(false);

        // Inner chart (e zbrazët për ta simuluar vrimën në mes)
        ObservableList<PieChart.Data> innerData = FXCollections.observableArrayList(
                new PieChart.Data("", 100) // Një rreth i vetëm
        );
        innerPieChart.setData(innerData);
        innerPieChart.setLabelsVisible(false);
        innerPieChart.setLegendVisible(false);
    }

}
