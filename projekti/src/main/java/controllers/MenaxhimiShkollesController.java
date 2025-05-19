package controllers;

import database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Shkolla;
import models.Drejtor;
import models.Adresa;
import utils.LanguageHandler;
import utils.SceneLocator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenaxhimiShkollesController {

    @FXML
    private TableView<Shkolla> tableSchools;

    @FXML
    private TableColumn<?, ?> columnSchoolId;


    @FXML
    private TableColumn<Shkolla, String> columnSchoolName;

    @FXML
    private TableColumn<Shkolla, String> columnPhone;

    @FXML
    private TableColumn<Shkolla, String> columnAddress;

    @FXML
    private TextField txtSchoolName, txtPhoneNumber;
    @FXML
    private TextField txtAddressStreet, txtAddressCity, txtAddressZip;

    @FXML
    private TableView<Drejtor> tableDirectors;

    @FXML
    private TableColumn<Drejtor, Integer> columnDirectorId;

    @FXML
    private TableColumn<Drejtor, String> columnDirectorName;

    @FXML
    private TableColumn<Drejtor, String> columnDirectorSurname;

    @FXML
    private TableColumn<Drejtor, String> columnDirectorPhone;

    @FXML
    private MenuButton menuLanguage;


    private ObservableList<Shkolla> schoolList = FXCollections.observableArrayList();
    private ObservableList<Drejtor> directorList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Columns for the School Table
        columnSchoolId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnSchoolName.setCellValueFactory(new PropertyValueFactory<>("emri"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("tel"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("adresa"));

        // Columns for the Director Table (Only partial data is shown)
        columnDirectorId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDirectorName.setCellValueFactory(new PropertyValueFactory<>("emri"));
        columnDirectorSurname.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        columnDirectorPhone.setCellValueFactory(new PropertyValueFactory<>("tel"));

        loadSchoolData();
        loadDirectorData();

        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.SCHOOL_MANAGEMENT_PAGE);

    }

    private void loadSchoolData() {
        schoolList.clear();
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM shkolla");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Shkolla shkolla = Shkolla.getInstance(rs);
                schoolList.add(shkolla);
            }
            tableSchools.setItems(schoolList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadDirectorData() {
        directorList.clear();
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM drejtori");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Drejtor drejtor = Drejtor.getInstance(rs);
                directorList.add(drejtor);
            }
            tableDirectors.setItems(directorList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddSchool(ActionEvent event) {
        System.out.println("Shto shkollën");
    }

    @FXML
    private void handleUpdateSchool(ActionEvent event) {
        System.out.println("Përditëso shkollën");
    }

    @FXML
    private void handleDeleteSchool(ActionEvent event) {
        System.out.println("Fshij shkollën");
    }
}
