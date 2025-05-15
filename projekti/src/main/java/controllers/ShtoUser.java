package controllers;

import database.DBConnector;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShtoUser {

    @FXML
    private TextField txtEmri;

    @FXML
    private TextField txtMbiemri;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPerdoruesi;

    @FXML
    private PasswordField txtFjalekalimi;

    @FXML
    private PasswordField txtFjalekalimi2;

    @FXML
    private RadioButton radioDrejtor;

    @FXML
    private RadioButton radioMesues;

    @FXML
    private RadioButton radioNxenes;

    private ToggleGroup roleGroup;

    @FXML
    public void initialize() {
        // Inicializimi i grupit
        roleGroup = new ToggleGroup();

        // Shtimi i butonave në grup
        radioDrejtor.setToggleGroup(roleGroup);
        radioMesues.setToggleGroup(roleGroup);
        radioNxenes.setToggleGroup(roleGroup);

        // Mos selekto asnjë si default
        roleGroup.selectToggle(null);
    }

    @FXML
    private void handleRegister() {
        // ✅ Validimi bazik
        if (txtEmri.getText().isEmpty() || txtMbiemri.getText().isEmpty() ||
                txtEmail.getText().isEmpty() || txtPerdoruesi.getText().isEmpty() ||
                txtFjalekalimi.getText().isEmpty() || txtFjalekalimi2.getText().isEmpty() ||
                roleGroup.getSelectedToggle() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim");
            alert.setHeaderText("Të dhëna të paplotësuara");
            alert.setContentText("Ju lutem plotësoni të gjitha fushat!");
            alert.showAndWait();
            return;
        }

        // ✅ Kontrolli i fjalëkalimit
        if (!txtFjalekalimi.getText().equals(txtFjalekalimi2.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim");
            alert.setHeaderText("Fjalëkalimet nuk përputhen");
            alert.setContentText("Ju lutem kontrolloni fjalëkalimet!");
            alert.showAndWait();
            return;
        }

        // ✅ Marrja e të dhënave
        String emri = txtEmri.getText();
        String mbiemri = txtMbiemri.getText();
        String email = txtEmail.getText();
        String perdoruesi = txtPerdoruesi.getText();
        String fjalekalimi = txtFjalekalimi.getText();
        String roli = ((RadioButton) roleGroup.getSelectedToggle()).getText();

        // ✅ Regjistrimi në databazë
        String query = "INSERT INTO users (emri, mbiemri, email, emri_perdoruesit, fjalekalimi, roli) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (connection == null) {
                System.out.println("❌ Lidhja me databazën nuk u realizua.");
                return;
            }

            System.out.println("✅ Lidhja me databazën u realizua me sukses.");

            // Vendos të dhënat për ruajtje
            statement.setString(1, emri);
            statement.setString(2, mbiemri);
            statement.setString(3, email);
            statement.setString(4, perdoruesi);
            statement.setString(5, fjalekalimi);
            statement.setString(6, roli);

            // Ekzekuto query-n
            System.out.println("📝 Po regjistrohet në databazë...");
            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Të dhënat u ruajtën me sukses.");

                // ✅ Pastrimi i fushave pas regjistrimit
                txtEmri.clear();
                txtMbiemri.clear();
                txtEmail.clear();
                txtPerdoruesi.clear();
                txtFjalekalimi.clear();
                txtFjalekalimi2.clear();
                roleGroup.selectToggle(null);

                // ✅ Shfaq mesazh suksesi
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sukses");
                alert.setHeaderText("Regjistrimi u krye me sukses!");
                alert.setContentText("Përdoruesi është ruajtur në databazë.");
                alert.showAndWait();
            } else {
                System.out.println("❌ Asnjë rresht nuk u ruajt në databazë.");
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim");
            alert.setHeaderText("Regjistrimi dështoi");
            alert.setContentText("Ka ndodhur një gabim gjatë regjistrimit: " + e.getMessage());
            alert.showAndWait();
            System.out.println("❌ Gabim gjatë ruajtjes: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
