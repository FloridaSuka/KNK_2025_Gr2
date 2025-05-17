package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import utils.LanguageHandler;
import utils.SceneLocator;

public class MenaxhimiShkollesController {
    @FXML
    private MenuButton menuLanguage;

    @FXML public void initialize() {
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.SCHOOL_MANAGEMENT_PAGE);
    }
}
