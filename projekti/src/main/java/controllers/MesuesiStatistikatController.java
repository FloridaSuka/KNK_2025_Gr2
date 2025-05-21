package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import utils.LanguageHandler;
import utils.MenuUtils;
import utils.SceneLocator;

public class MesuesiStatistikatController {

    @FXML
    private LineChart<String, Number> lineChartKlasa;

    @FXML
    private LineChart<String, Number> lineChartGjinia;

    @FXML
    private MenuButton menuLanguage;

    @FXML
    public void initialize() {
        setupKlasaChart();
        setupGjiniaChart();
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.HELP_PAGE);
        String name = this.getClass().getSimpleName();
        System.out.println("🔍 Controller aktiv: " + name);
        MenuUtils.populateOpenSubMenu(menuOpen, name);
    }
    @FXML private MenuItem menuCut, menuCopy, menuPaste, menuUndo, menuSelectAll, menuRedo;
    @FXML private Menu menuOpen;

    @FXML
    public void handleNew(ActionEvent event) {
        MenuUtils.handleNew();
    }

    @FXML
    public void handleOpen() {
        // Shembull: ky controller është për admin
        MenuUtils.openConditionalView("MenaxhimiDrejtoreveController", "menaxhimiDrejtoreve.fxml", "Menaxhimi i Drejtoreve");
    }

    @FXML
    public void handleQuit() {
        System.exit(0);
    }

    @FXML
    public void handleUndo() {
        MenuUtils.performUndo(menuUndo.getParentPopup().getOwnerWindow().getScene());
    }

    @FXML
    public void handleRedo() {
        MenuUtils.performRedo(menuRedo.getParentPopup().getOwnerWindow().getScene());
    }

    @FXML
    public void handleCut() {
        MenuUtils.performCut(menuCut.getParentPopup().getOwnerWindow().getScene());
    }

    @FXML
    public void handleCopy() {
        MenuUtils.performCopy(menuCopy.getParentPopup().getOwnerWindow().getScene());
    }

    @FXML
    public void handlePaste() {
        MenuUtils.performPaste(menuPaste.getParentPopup().getOwnerWindow().getScene());
    }

    @FXML
    public void handleSelectAll() {
        MenuUtils.performSelectAll(menuSelectAll.getParentPopup().getOwnerWindow().getScene());
    }

    @FXML
    public void handleHelp() {
        MenuUtils.openhelp();
    }

    private void setupKlasaChart() {
        CategoryAxis xAxis = (CategoryAxis) lineChartKlasa.getXAxis();
        NumberAxis yAxis = (NumberAxis) lineChartKlasa.getYAxis();

        xAxis.setLabel("Klasa");
        yAxis.setLabel("Numri i Nxënësve");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Nxënësit sipas klasës");

        // Shembull i dhënash
        series.getData().add(new XYChart.Data<>("Klasa 9", 48));
        series.getData().add(new XYChart.Data<>("Klasa 10", 42));
        series.getData().add(new XYChart.Data<>("Klasa 11", 53));
        series.getData().add(new XYChart.Data<>("Klasa 12", 39));

        lineChartKlasa.getData().clear();
        lineChartKlasa.getData().add(series);
    }

    private void setupGjiniaChart() {
        CategoryAxis xAxis = (CategoryAxis) lineChartGjinia.getXAxis();
        NumberAxis yAxis = (NumberAxis) lineChartGjinia.getYAxis();

        xAxis.setLabel("Gjinia");
        yAxis.setLabel("Numri i Nxënësve");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Nxënësit sipas gjinise");

        // Shembull i dhënash
        series.getData().add(new XYChart.Data<>("Mashkull", 102));
        series.getData().add(new XYChart.Data<>("Femër", 108));

        lineChartGjinia.getData().clear();
        lineChartGjinia.getData().add(series);
    }
}

