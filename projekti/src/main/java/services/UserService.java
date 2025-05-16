package services;

import database.DBConnector;
import models.User;
import models.User.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

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
            // 👉 Këtu mbyllim vetëm ResultSet dhe PreparedStatement, por jo Connection
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                // **Lidhja me databazën nuk mbyllet këtu!**
                System.out.println("⚠️ ResultSet dhe PreparedStatement u mbyllën. Lidhja mbetet e hapur.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // ✅ Regjistrimi i përdoruesit në databazë
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
}
