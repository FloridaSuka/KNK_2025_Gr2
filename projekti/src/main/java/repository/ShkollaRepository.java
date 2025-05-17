package repository;

import database.DBConnector;
import models.Adresa;
import models.Shkolla;
import main.java.models.dto.create.CreateShkolla;
import main.java.models.dto.delete.DeleteShkolla;
import main.java.models.dto.update.UpdateShkolla;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShkollaRepository {

    private final Connection connection;

    public ShkollaRepository() throws SQLException {
        this.connection =DBConnector.getConnection();
    }

    public List<Shkolla> getAll() throws SQLException {
        List<Shkolla> shkollat = new ArrayList<>();
        String query = "SELECT * FROM shkolla JOIN adresa ON shkolla.adresa_id = adresa.id";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            shkollat.add(Shkolla.getInstance(rs));
        }

        return shkollat;
    }

    public void create(CreateShkolla dto) throws SQLException {
        String query = "INSERT INTO shkolla (id, emri, tel, adresa_id) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, dto.getId());
        stmt.setString(2, dto.getEmri());
        stmt.setString(3, dto.getTel());
        stmt.setInt(4, dto.getAdresa().getId());
        stmt.executeUpdate();
    }

    public void update(UpdateShkolla dto) throws SQLException {
        String query = "UPDATE shkolla SET emri = ?, tel = ?, adresa_id = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, dto.getEmri());
        stmt.setString(2, dto.getTel());
        stmt.setInt(3, dto.getAdresa().getId());
        stmt.setInt(4, dto.getId());
        stmt.executeUpdate();
    }

    public void delete(DeleteShkolla dto) throws SQLException {
        String query = "DELETE FROM shkolla WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, dto.getId());
        stmt.executeUpdate();
    }
}

