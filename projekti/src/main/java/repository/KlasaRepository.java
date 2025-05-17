package repository;

import database.DBConnector;
import models.Klasa;
import models.dto.create.CreateKlasa;
import models.dto.update.UpdateKlasa;
import models.dto.delete.DeleteKlasa;

import java.sql.*;
import java.util.ArrayList;

public class KlasaRepository {

    public static ArrayList<Klasa> getAll() {
        ArrayList<Klasa> klasat = new ArrayList<>();
        String query = "SELECT * FROM Klasa";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                klasat.add(Klasa.getInstance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return klasat;
    }

    public static Klasa getById(int id) {
        String query = "SELECT * FROM Klasa WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return Klasa.getInstance(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean create(CreateKlasa klasa) {
        String query = "INSERT INTO Klasa (id, niveli, shkolla_id, paralelja_id, profesori_id, drejtimi_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, klasa.getId());
            pst.setInt(2, klasa.getNiveli());
            pst.setInt(3, klasa.getShkollaId());
            pst.setInt(4, klasa.getParaleljaId());
            pst.setInt(5, klasa.getProfesoriId());
            pst.setInt(6, klasa.getDrejtimiId());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(UpdateKlasa klasa) {
        String query = "UPDATE Klasa SET niveli = ?, shkolla_id = ?, paralelja_id = ?, profesori_id = ?, drejtimi_id = ? WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, klasa.getNiveli());
            pst.setInt(2, klasa.getShkollaId());
            pst.setInt(3, klasa.getParaleljaId());
            pst.setInt(4, klasa.getProfesoriId());
            pst.setInt(5, klasa.getDrejtimiId());
            pst.setInt(6, klasa.getId());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(DeleteKlasa klasa) {
        String query = "DELETE FROM Klasa WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, klasa.getId());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

