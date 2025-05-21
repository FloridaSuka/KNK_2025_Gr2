package repositories;

import database.DBConnector;
import models.dto.create.CreateShkolla;
import models.dto.update.UpdateShkolla;

import java.sql.*;

public class ShkollaRepository {

    public boolean shtoShkollen(CreateShkolla shkolla) {
        String sql = "INSERT INTO shkolla (emri, tel, adresa_id) VALUES (?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, shkolla.getEmri());
            stmt.setString(2, shkolla.getTel());
            stmt.setInt(3, shkolla.getAdresaId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean perditesoShkollen(UpdateShkolla shkolla) {
        String sql = "UPDATE shkolla SET emri = ?, tel = ?, adresa_id = ? WHERE id = ?";
        try (Connection conn = DBConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, shkolla.getEmri());
            stmt.setString(2, shkolla.getTel());
            stmt.setInt(3, shkolla.getAdresaId());
            stmt.setInt(4, shkolla.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean fshijShkollen(int id) {
        String sql = "DELETE FROM shkolla WHERE id = ?";
        try (Connection conn = DBConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
