package repository;

import database.DBConnector;
import models.Drejtimi;
import models.dto.create.CreateDrejtimi;
import models.dto.update.UpdateDrejtimi;
import models.dto.delete.DeleteDrejtimi;

import java.sql.*;
import java.util.ArrayList;

public class DrejtimiRepository {

    public static ArrayList<Drejtimi> getAll() {
        ArrayList<Drejtimi> drejtimet = new ArrayList<>();
        String query = "SELECT * FROM Drejtimi";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                drejtimet.add(Drejtimi.getInstance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return drejtimet;
    }

    public static Drejtimi getById(int id) {
        String query = "SELECT * FROM Drejtimi WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return Drejtimi.getInstance(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean create(CreateDrejtimi drejtimi) {
        String query = "INSERT INTO Drejtimi (emri, shkolla_id, paralelja_id) VALUES (?, ?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, drejtimi.getEmri());
            pst.setInt(2, drejtimi.getShkollaId());
            pst.setInt(3, drejtimi.getParaleljaId());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(UpdateDrejtimi drejtimi) {
        String query = "UPDATE Drejtimi SET emri = ?, shkolla_id = ?, paralelja_id = ? WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, drejtimi.getEmri());
            pst.setInt(2, drejtimi.getShkollaId());
            pst.setInt(3, drejtimi.getParaleljaId());
            pst.setInt(4, drejtimi.getId());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(DeleteDrejtimi drejtimi) {
        String query = "DELETE FROM Drejtimi WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, drejtimi.getId());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

