package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import utils.LanguageHandler;
import utils.SceneLocator;
import utils.SceneNavigator;

import java.io.IOException;
import java.net.URL;

import static utils.SceneLocator.CLASS_MANAGEMENT_PAGE;

public class DrejtorController {



    //PJESA E GJUHES
    @FXML
    private MenuButton menuLanguage;


    @FXML
    public void initialize() {
        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.ADMIN_PAGE);
    }
    @FXML
    private void handleTeacher(ActionEvent event) {
        SceneNavigator.switchScene((Node) event.getSource(), SceneLocator.TEACHER_MANAGEMENT_PAGE);
    }
    @FXML
    private void handlePrincipalStatistics(ActionEvent event){
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
}


