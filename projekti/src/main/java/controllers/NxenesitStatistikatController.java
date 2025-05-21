package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import utils.MenuUtils;

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
        // Vendos të dhëna shembull në grafikun rrethor
        pieChart.getData().clear();
        pieChart.getData().addAll(
                new PieChart.Data("Matematikë", 40),
                new PieChart.Data("Fizikë", 25),
                new PieChart.Data("Kimikë", 20),
                new PieChart.Data("Biologji", 15)
        );

        // Vendos listeners për zgjedhjen e gjuhës
        menuAL.setOnAction(e -> switchLanguage("ALB"));
        menuEN.setOnAction(e -> switchLanguage("ENG"));
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

    private void switchLanguage(String lang) {
        // Këtu mund të shtosh logjikën për ndërrimin e gjuhës në UI
        System.out.println("Gjuha u ndryshua në: " + lang);

        // Për shembull: ndrysho titullin e grafikut
        if ("ALB".equals(lang)) {
            pieChart.setTitle("Përqindja sipas lëndës");
            menuLanguage.setText("Gjuha");
        } else if ("ENG".equals(lang)) {
            pieChart.setTitle("Percentage by Subject");
            menuLanguage.setText("Language");
        }
    }
}

