package controllers;

import database.DBConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.User;
import services.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SettingsController {
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtEmri;

    @FXML
    private TextField txtMbiemri;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtUsername;

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
    private void handleFshijLlogarine() {
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
                        txtId.clear();
                        txtEmri.clear();
                        txtMbiemri.clear();
                        txtEmail.clear();
                        txtUsername.clear();

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
}

