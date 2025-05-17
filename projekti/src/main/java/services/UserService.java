package services;

import database.DBConnector;
import models.User;
import models.User.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    // ✅ Metoda për autentikim (ekzistuese)
    public User authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = DBConnector.getConnection();

            if (connection == null) {
                System.out.println("❌ Lidhja me databazën dështoi!");
                return null;
            }

            System.out.println("✅ Lidhja u krijua me sukses!");

            statement = connection.prepareStatement(query);

            // 👉 Kontrollojmë nëse username dhe password janë bosh
            if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
                System.out.println("❌ Username ose Password janë bosh.");
                return null;
            }

            statement.setString(1, username);
            statement.setString(2, password);

            result = statement.executeQuery();

            if (result.next()) {
                System.out.println("✅ Përdoruesi u gjet!");
                return User.getInstance(result);
            } else {
                System.out.println("❌ Përdoruesi nuk u gjet.");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("❌ Gabim gjatë ekzekutimit të query: " + e.getMessage());
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                System.out.println("⚠️ ResultSet dhe PreparedStatement u mbyllën. Lidhja mbetet e hapur.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // ✅ Regjistrimi i përdoruesit në databazë (ekzistuese)
    public boolean register(User user) {
        String query = "INSERT INTO users (username, password, email, name, surname, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (connection == null) {
                System.out.println("❌ Lidhja me databazën dështoi!");
                return false;
            }

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getEmer());
            statement.setString(5, user.getMbiemer());
            statement.setString(6, user.getRole().toString());

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Përdoruesi u regjistrua me sukses në databazë!");
                return true;
            } else {
                System.out.println("❌ Regjistrimi dështoi.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("❌ Gabim gjatë ekzekutimit të query: " + e.getMessage());
            return false;
        }
    }

    public boolean verifyUser(String username, String verificationCode) {
        String query;
        boolean isNumeric = verificationCode.matches("\\d+"); // Kontrollo nëse është numerik

        if (isNumeric) {
            query = "SELECT * FROM users WHERE username = ? AND id = ?";
        } else {
            query = "SELECT * FROM users WHERE username = ? AND password = ?";
        }

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);

            if (isNumeric) {
                statement.setInt(2, Integer.parseInt(verificationCode)); // Kthehet në integer
            } else {
                statement.setString(2, verificationCode);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Kthen true nëse ekziston, false nëse jo
            }
        } catch (SQLException e) {
            System.out.println("❌ Gabim gjatë ekzekutimit të query: " + e.getMessage());
            return false;
        }
    }


    // ✅ 2. Metoda për të përditësuar fjalëkalimin e përdoruesit
    public boolean updatePassword(String username, String newPassword) {
        String query = "UPDATE users SET password = ? WHERE username = ?";

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newPassword);
            statement.setString(2, username);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("❌ Gabim gjatë ekzekutimit të query: " + e.getMessage());
            return false;
        }
    }
}
