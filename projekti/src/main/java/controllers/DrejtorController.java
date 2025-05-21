package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import utils.LanguageHandler;
import utils.MenuUtils;
import utils.SceneLocator;
import utils.SceneNavigator;

public class DrejtorController {

    // Gjuha
    @FXML private MenuButton menuLanguage;
    @FXML private MenuItem menuAL, menuEN;

    // Menu Items
    @FXML private MenuItem menuNew, menuOpenItem, menuQuit;
    @FXML private MenuItem menuCut, menuCopy, menuPaste, menuUndo, menuSelectAll, menuRedo, menuHelp;

    @FXML
    public void initialize() {
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.ADMIN_PAGE);
        System.out.println("🔍 Controller aktiv: " + getClass().getSimpleName());
    }

    @FXML
    public void handleNew(ActionEvent event) {
        MenuUtils.handleNew();
    }

    @FXML
    public void handleOpen(ActionEvent event) {
        MenuUtils.openConditionalView("MenaxhimiDrejtoreveController", "menaxhimiDrejtoreve.fxml", "Menaxhimi i Drejtoreve");
    }

    @FXML
    public void handleQuit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void handleUndo(ActionEvent event) {
        MenuUtils.performUndo(getSceneFrom(menuUndo));
    }

    @FXML
    public void handleRedo(ActionEvent event) {
        MenuUtils.performRedo(getSceneFrom(menuRedo));
    }

    @FXML
    public void handleCut(ActionEvent event) {
        MenuUtils.performCut(getSceneFrom(menuCut));
    }

    @FXML
    public void handleCopy(ActionEvent event) {
        MenuUtils.performCopy(getSceneFrom(menuCopy));
    }

    @FXML
    public void handlePaste(ActionEvent event) {
        MenuUtils.performPaste(getSceneFrom(menuPaste));
    }

    @FXML
    public void handleSelectAll(ActionEvent event) {
        MenuUtils.performSelectAll(getSceneFrom(menuSelectAll));
    }

    @FXML
    public void handleHelp(ActionEvent event) {
        MenuUtils.openhelp();
    }

    @FXML
    private void handleTeacher(ActionEvent event) {
        SceneNavigator.switchScene((Node) event.getSource(), SceneLocator.TEACHER_MANAGEMENT_PAGE);
    }

    @FXML
    private void handlePrincipalStatistics(ActionEvent event) {
        SceneNavigator.switchScene((Node) event.getSource(), SceneLocator.PRINCIPAL_STATISTICS_PAGE);
    }

    @FXML
    private void handleSubject(ActionEvent event) {
        SceneNavigator.switchScene((Node) event.getSource(), SceneLocator.SUBJECT_MANAGEMENT_PAGE);
    }

    @FXML
    private void handleClass(ActionEvent event) {
        SceneNavigator.switchScene((Node) event.getSource(), SceneLocator.CLASS_MANAGEMENT_PAGE);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        SceneNavigator.logout((Node) event.getSource());
    }

    @FXML
    private void onOpenSettings(ActionEvent event) {
        SceneNavigator.switchScene((Node) event.getSource(), SceneLocator.SETTINGS_PAGE);
    }

    // Ndihmëse për të marrë skenën
    private javafx.scene.Scene getSceneFrom(MenuItem item) {
        if (item.getParentPopup() != null && item.getParentPopup().getOwnerWindow() != null) {
            return item.getParentPopup().getOwnerWindow().getScene();
        }
        return null;
    }
}
