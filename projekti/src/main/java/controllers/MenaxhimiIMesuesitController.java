package controllers;

import database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Mesuesi;
import utils.LanguageHandler;
import utils.SceneLocator;
import utils.SceneNavigator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenaxhimiIMesuesitController {
    @FXML
    private void handleGoBack(ActionEvent event) {
        System.out.println("🔄 Duke u kthyer prapa...");
        SceneNavigator.goBack((Node) event.getSource());
    }
//
//    @FXML
//    private TableView<Mesuesi> tableTeachers;
//
//    @FXML
//    private TableColumn<Mesuesi, Integer> columnTeacherId;
//
//    @FXML
//    private TableColumn<Mesuesi, String> columnTeacherName;
//
//    @FXML
//    private TableColumn<Mesuesi, String> columnTeacherSurname;
//
//    @FXML
//    private TableColumn<Mesuesi, String> columnTeacherPhone;
//
//    @FXML
//    private TableColumn<Mesuesi, String> columnTeacherSchool;
//
//    @FXML
//    private TextField txtTeacherName, txtTeacherSurname, txtTeacherPhone;
//
//    @FXML
//    private ComboBox<String> comboSchool; // Ose nëse e ke `Shkolla` si objekt, përdor `ComboBox<Shkolla>`
//
//    @FXML
//    private MenuButton menuLanguage;
//
//    private ObservableList<Mesuesi> teacherList = FXCollections.observableArrayList();
//
//    @FXML
//    public void initialize() {
//        // Table columns
//        columnTeacherId.setCellValueFactory(new PropertyValueFactory<>("id"));
//        columnTeacherName.setCellValueFactory(new PropertyValueFactory<>("emri"));
//        columnTeacherSurname.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
//        columnTeacherPhone.setCellValueFactory(new PropertyValueFactory<>("tel"));
//        columnTeacherSchool.setCellValueFactory(new PropertyValueFactory<>("shkollaEmri")); // duhet ta kthesh si string nga modeli
//
//        loadTeacherData();
//        LanguageHandler.configureLanguageMenu(menuLanguage, SceneLocator.TEACHER_PAGE);
//    }
//
//    private void loadTeacherData() {
//        teacherList.clear();
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement stmt = conn.prepareStatement("SELECT m.id, m.emri, m.mbiemri, m.tel, s.emri AS shkollaEmri FROM mesuesi m JOIN shkolla s ON m.shkolla_id = s.id");
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                Mesuesi mesuesi = Mesuesi.getInstance(rs);
//                teacherList.add(mesuesi);
//            }
//            tableTeachers.setItems(teacherList);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void handleAddTeacher(ActionEvent event) {
//        System.out.println("Shto mësuesin");
//    }
//
//    @FXML
//    private void handleUpdateTeacher(ActionEvent event) {
//        System.out.println("Përditëso mësuesin");
//    }
//
//    @FXML
//    private void handleDeleteTeacher(ActionEvent event) {
//        System.out.println("Fshij mësuesin");
//    }
}



