package repository;

import database.DBConnector;
import models.dto.create.CreateAdministratori;
import models.dto.update.UpdateAdministratori;
import models.dto.delete.DeleteAdministratori;

import java.sql.*;
import java.util.ArrayList;

public class AdministratorRepository {

    public static ArrayList<Administratori> getAll() {
        ArrayList<Administratori> lista = new ArrayList<>();
        String query = "SELECT * FROM Administratori INNER JOIN User ON Administratori.ID = User.id";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                lista.add(Administratori.getInstance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }

    public static Administratori getById(int id) {
        String query = "SELECT * FROM Administratori INNER JOIN User ON Administratori.ID = User.id WHERE Administratori.ID = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return Administratori.getInstance(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean create(CreateAdministratori admin) {
        String query = "INSERT INTO Administratori (ID, emailZyrtar, telefoni, departamenti, aktiv, dataKrijimit) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, admin.getID());
            pst.setString(2, admin.getEmailZyrtar());
            pst.setString(3, admin.getTelefoni());
            pst.setString(4, admin.getDepartamenti());
            pst.setBoolean(5, admin.isAktiv());
            pst.setTimestamp(6, Timestamp.valueOf(admin.getDataKrijimit()));

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(UpdateAdministratori admin) {
        String query = "UPDATE Administratori SET emailZyrtar = ?, telefoni = ?, departamenti = ?, aktiv = ? WHERE ID = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, admin.getEmailZyrtar());
            pst.setString(2, admin.getTelefoni());
            pst.setString(3, admin.getDepartamenti());
            pst.setBoolean(4, admin.isAktiv());
            pst.setInt(5, admin.getId());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(DeleteAdministratori admin) {
        String query = "DELETE FROM Administratori WHERE ID = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, admin.getId());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

