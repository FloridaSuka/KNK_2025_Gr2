package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import utils.LanguageHandler;
import utils.SceneLocator;
import utils.SceneNavigator;

public class AdminController {

    @FXML private MenuButton menuLanguage;

    @FXML public void initialize() {
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.LOGIN_PAGE);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        SceneNavigator.logout((Node) event.getSource());
    }
}
