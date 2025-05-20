//package repositories;
//
//import database.DBConnector;
//import models.Mesimi;
//import models.dto.create.CreateMesimi;
//import models.dto.update.UpdateMesimi;
//import models.dto.delete.DeleteMesimi;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//public class MesimiRepository {
//
//    public static ArrayList<Mesimi> getAll() {
//        ArrayList<Mesimi> mesimet = new ArrayList<>();
//        String query = "SELECT * FROM Mesimi";
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement pst = conn.prepareStatement(query);
//             ResultSet rs = pst.executeQuery()) {
//
//            while (rs.next()) {
//                mesimet.add(Mesimi.getInstance(rs));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return mesimet;
//    }
//
//    public static Mesimi getByLendaId(int lendaId) {
//        String query = "SELECT * FROM Mesimi WHERE lid = ?";
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement pst = conn.prepareStatement(query)) {
//
//            pst.setInt(1, lendaId);
//            ResultSet rs = pst.executeQuery();
//            if (rs.next()) {
//                return Mesimi.getInstance(rs);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static boolean create(CreateMesimi mesimi) {
//        String query = "INSERT INTO Mesimi (lid, pid, klid, did) VALUES (?, ?, ?, ?)";
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement pst = conn.prepareStatement(query)) {
//
//            pst.setInt(1, mesimi.getLendaId());
//            pst.setInt(2, mesimi.getProfesoriId());
//            pst.setInt(3, mesimi.getKlasaId());
//            pst.setInt(4, mesimi.getDrejtimiId());
//
//            return pst.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public static boolean update(UpdateMesimi mesimi) {
//        String query = "UPDATE Mesimi SET pid = ?, klid = ?, did = ? WHERE lid = ?";
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement pst = conn.prepareStatement(query)) {
//
//            pst.setInt(1, mesimi.getProfesoriId());
//            pst.setInt(2, mesimi.getKlasaId());
//            pst.setInt(3, mesimi.getDrejtimiId());
//            pst.setInt(4, mesimi.getLendaId());
//
//            return pst.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public static boolean delete(DeleteMesimi mesimi) {
//        String query = "DELETE FROM Mesimi WHERE lid = ?";
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement pst = conn.prepareStatement(query)) {
//
//            pst.setInt(1, mesimi.getLendaId());
//
//            return pst.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//}
//
