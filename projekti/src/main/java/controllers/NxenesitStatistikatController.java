package controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class NxenesitStatistikatController {

    @FXML
    private PieChart pieChart;

    @FXML
    private MenuButton menuLanguage;

    @FXML
    private MenuItem menuAL;

    @FXML
    private MenuItem menuEN;

    @FXML
    public void initialize() {
        pieChart.getData().clear();
        pieChart.getData().addAll(
                new PieChart.Data("Matematikë", 40),
                new PieChart.Data("Fizikë", 25),
                new PieChart.Data("Kimikë", 20),
                new PieChart.Data("Biologji", 15)
        );

        menuAL.setOnAction(e -> switchLanguage("ALB"));
        menuEN.setOnAction(e -> switchLanguage("ENG"));
    }


    private void switchLanguage(String lang) {
        System.out.println("Gjuha u ndryshua në: " + lang);

        if ("ALB".equals(lang)) {
            pieChart.setTitle("Përqindja sipas lëndës");
            menuLanguage.setText("Gjuha");
        } else if ("ENG".equals(lang)) {
            pieChart.setTitle("Percentage by Subject");
            menuLanguage.setText("Language");
        }
    }
}

