//package repositories;
//
//import database.DBConnector;
//import models.Lenda;
//import models.dto.create.CreateLenda;
//import models.dto.update.UpdateLenda;
//import models.dto.delete.DeleteLenda;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//public class LendaRepository {
//
//    public static ArrayList<Lenda> getAll() {
//        ArrayList<Lenda> lendet = new ArrayList<>();
//        String query = "SELECT * FROM Lenda";
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement pst = conn.prepareStatement(query);
//             ResultSet rs = pst.executeQuery()) {
//
//            while (rs.next()) {
//                lendet.add(new Lenda(
//                        rs.getInt("id"),
//                        rs.getString("emri"),
//                        rs.getInt("mesimi_id"),
//                        rs.getInt("drejtimi_id"),
//                        rs.getInt("perioda_id"),
//                        rs.getInt("mesuesi_id")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return lendet;
//    }
//
//    public static Lenda getById(int id) {
//        String query = "SELECT * FROM Lenda WHERE id = ?";
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement pst = conn.prepareStatement(query)) {
//
//            pst.setInt(1, id);
//            ResultSet rs = pst.executeQuery();
//            if (rs.next()) {
//                return new Lenda(
//                        rs.getInt("id"),
//                        rs.getString("emri"),
//                        rs.getInt("mesimi_id"),
//                        rs.getInt("drejtimi_id"),
//                        rs.getInt("perioda_id"),
//                        rs.getInt("mesuesi_id")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static boolean create(CreateLenda lenda) {
//        String query = "INSERT INTO Lenda (emri, mesimi_id, drejtimi_id, perioda_id, mesuesi_id) VALUES (?, ?, ?, ?, ?)";
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement pst = conn.prepareStatement(query)) {
//
//            pst.setString(1, lenda.getEmri());
//            pst.setInt(2, lenda.getIdMesimi());
//            pst.setInt(3, lenda.getIdDrejtimi());
//            pst.setInt(4, lenda.getIdPerioda());
//            pst.setInt(5, lenda.getIdMesuesi());
//
//            return pst.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public static boolean update(UpdateLenda lenda) {
//        String query = "UPDATE Lenda SET emri = ?, mesimi_id = ?, drejtimi_id = ?, perioda_id = ?, mesuesi_id = ? WHERE id = ?";
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement pst = conn.prepareStatement(query)) {
//
//            pst.setString(1, lenda.getEmri());
//            pst.setInt(2, lenda.getIdMesimi());
//            pst.setInt(3, lenda.getIdDrejtimi());
//            pst.setInt(4, lenda.getIdPerioda());
//            pst.setInt(5, lenda.getIdMesuesi());
//            pst.setInt(6, lenda.getIdLenda());
//
//            return pst.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public static boolean delete(DeleteLenda lenda) {
//        String query = "DELETE FROM Lenda WHERE id = ?";
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement pst = conn.prepareStatement(query)) {
//
//            pst.setInt(1, lenda.getId());
//
//            return pst.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//}
//
