package controllers;

import database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;
import services.UserService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SettingsController {
    @FXML
    private Label txtId;

    @FXML
    private Label txtEmri;

    @FXML
    private Label txtMbiemri;

    @FXML
    private Label txtEmail;

    @FXML
    private Label txtUsername;

    private final UserService userService = new UserService();

    public void initialize() {
        User user = UserService.getCurrentUser();

        if (user != null) {
            txtId.setText(String.valueOf(user.getId()));
            txtEmri.setText(user.getEmer());
            txtMbiemri.setText(user.getMbiemer());
            txtEmail.setText(user.getEmail());
            txtUsername.setText(user.getUsername());
        } else {
            System.out.println("User not found!");
        }
    }

    @FXML
    private void fshiLlogarine() {
        if (txtId.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fushë e Zbrazët");
            alert.setHeaderText(null);
            alert.setContentText("ID nuk është e plotësuar!");
            alert.showAndWait();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Konfirmo Fshirjen");
        confirm.setHeaderText("A je i sigurt që dëshiron ta fshish këtë llogari?");
        confirm.setContentText("Ky veprim nuk mund të kthehet mbrapsht.");

        confirm.showAndWait().ifPresent(response -> {
            if (response.getText().equals("OK")) {
                int userId = Integer.parseInt(txtId.getText());

                try {
                    String query = "DELETE FROM users WHERE id = ?";
                    Connection connection = DBConnector.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, userId);

                    int result = preparedStatement.executeUpdate();
                    if (result > 0) {
                        txtId.setText("");
                        txtEmri.setText("");
                        txtMbiemri.setText("");
                        txtEmail.setText("");
                        txtUsername.setText("");

                        Alert success = new Alert(Alert.AlertType.INFORMATION);
                        success.setTitle("Llogaria u fshi");
                        success.setHeaderText(null);
                        success.setContentText("Llogaria u fshi me sukses!");
                        success.showAndWait();
                    } else {
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setTitle("Gabim");
                        error.setHeaderText(null);
                        error.setContentText("Llogaria nuk u gjet ose fshirja dështoi.");
                        error.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Gabim gjatë fshirjes");
                    error.setHeaderText(null);
                    error.setContentText("Ndodhi një gabim gjatë fshirjes së llogarisë.");
                    error.showAndWait();
                }
            }
        });
    }


    public void onForgotPassword(ActionEvent actionEvent) {
    }


    @FXML
    public void ndryshoEmri() {
        String id = txtId.getText();
        String emriRi = txtEmri.getText();

        if (id.isEmpty() || emriRi.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fushë e zbrazët");
            alert.setHeaderText(null);
            alert.setContentText("ID ose Emri janë të zbrazët.");
            alert.showAndWait();
            return;
        }

        try {
            Connection conn = DBConnector.getConnection();
            String query = "UPDATE users SET emer = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, emriRi);
            stmt.setInt(2, Integer.parseInt(id));
            int result = stmt.executeUpdate();

            Alert alert = new Alert(result > 0 ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
            alert.setTitle(result > 0 ? "Sukses" : "Gabim");
            alert.setHeaderText(null);
            alert.setContentText(result > 0 ? "Emri u përditësua me sukses!" : "Emri nuk u përditësua.");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim në lidhje");
            alert.setHeaderText(null);
            alert.setContentText("Gabim gjatë përditësimit të emrit.");
            alert.showAndWait();
        }
    }

    @FXML
    public void ndryshoMbiemri() {
        String id = txtId.getText();
        String mbiemriRi = txtMbiemri.getText();

        if (id.isEmpty() || mbiemriRi.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fushë e zbrazët");
            alert.setHeaderText(null);
                alert.setContentText("ID ose Mbiemri janë të zbrazët.");
            alert.showAndWait();
            return;
        }

        try {
            Connection conn = DBConnector.getConnection();
            String query = "UPDATE users SET mbiemer = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, mbiemriRi);
            stmt.setInt(2, Integer.parseInt(id));
            int result = stmt.executeUpdate();

            Alert alert = new Alert(result > 0 ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
            alert.setTitle(result > 0 ? "Sukses" : "Gabim");
            alert.setHeaderText(null);
            alert.setContentText(result > 0 ? "Mbiemri u përditësua me sukses!" : "Mbiemri nuk u përditësua.");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim në lidhje");
            alert.setHeaderText(null);
            alert.setContentText("Gabim gjatë përditësimit të mbiemrit.");
            alert.showAndWait();
        }
    }


    @FXML
    public void ndryshoEmail(ActionEvent actionEvent) {
        String id = txtId.getText();
        String emailRi = txtEmail.getText();

        if (id.isEmpty() || emailRi.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fushë e zbrazët");
            alert.setHeaderText(null);
            alert.setContentText("ID ose Email janë të zbrazët.");
            alert.showAndWait();
            return;
        }

        try {
            Connection conn = DBConnector.getConnection();
            String query = "UPDATE users SET emer = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, emailRi);
            stmt.setInt(2, Integer.parseInt(id));
            int result = stmt.executeUpdate();

            Alert alert = new Alert(result > 0 ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
            alert.setTitle(result > 0 ? "Sukses" : "Gabim");
            alert.setHeaderText(null);
            alert.setContentText(result > 0 ? "Email u përditësua me sukses!" : "Email nuk u përditësua.");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim në lidhje");
            alert.setHeaderText(null);
            alert.setContentText("Gabim gjatë përditësimit të email.");
            alert.showAndWait();
        }
    }


    @FXML
    public void ndryshoUser() {
        String id = txtId.getText();
        String userRi = txtUsername.getText();

        if (id.isEmpty() || userRi.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fushë e zbrazët");
            alert.setHeaderText(null);
            alert.setContentText("ID ose Username janë të zbrazët.");
            alert.showAndWait();
            return;
        }

        try {
            Connection conn = DBConnector.getConnection();
            String query = "UPDATE users SET username = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userRi);
            stmt.setInt(2, Integer.parseInt(id));
            int result = stmt.executeUpdate();

            Alert alert = new Alert(result > 0 ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
            alert.setTitle(result > 0 ? "Sukses" : "Gabim");
            alert.setHeaderText(null);
            alert.setContentText(result > 0 ? "Username u përditësua me sukses!" : "Username nuk u përditësua.");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim në lidhje");
            alert.setHeaderText(null);
            alert.setContentText("Gabim gjatë përditësimit të username.");
            alert.showAndWait();
        }
    }

}

