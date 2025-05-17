package repository;

import database.DBConnector;
import models.User;
import models.User.Role;
import models.dto.create.CreateUser;
import models.dto.delete.DeleteUser;
import models.dto.update.UpdateUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final Connection connection;

    public UserRepository() throws SQLException {
        this.connection = DBConnector.getConnection();
    }

    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            users.add(User.getInstance(rs));
        }

        return users;
    }

    public void create(CreateUser dto) throws SQLException {
        String sql = "INSERT INTO users (username, password, email, name, surname, role) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, dto.getUsername());
        stmt.setString(2, dto.getPassword()); // Hash nëse s'është hashed!
        stmt.setString(3, dto.getEmail());
        stmt.setString(4, dto.getEmer());
        stmt.setString(5, dto.getMbiemer());
        stmt.setString(6, dto.getRole().toUpperCase());
        stmt.executeUpdate();
    }

    public void update(UpdateUser dto) throws SQLException {
        String sql = "UPDATE users SET password = ?, email = ?, name = ?, surname = ?, role = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, dto.getPassword()); // Kujdes me hash
        stmt.setString(2, dto.getEmail());
        stmt.setString(3, dto.getEmer());
        stmt.setString(4, dto.getMbiemer());
        stmt.setString(5, dto.getRole().toUpperCase());
        stmt.setInt(6, dto.getId());
        stmt.executeUpdate();
    }

    public void delete(DeleteUser dto) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, dto.getId());
        stmt.executeUpdate();
    }

    public User getById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return User.getInstance(rs);
        }
        return null;
    }

    public User getByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return User.getInstance(rs);
        }
        return null;
    }
}

