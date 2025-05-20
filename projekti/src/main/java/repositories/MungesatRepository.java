//package repositories;
//
//import database.DBConnector;
//import models.Mungesa;
//import models.dto.create.CreateMungesa;
//import models.dto.update.UpdateMungesa;
//import models.dto.delete.DeleteMungesa;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//public class MungesatRepository {
//
//    public static ArrayList<Mungesa> getAll() {
//        ArrayList<Mungesa> list = new ArrayList<>();
//        String sql = "SELECT * FROM Mungesa";
//
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String student = rs.getString("student");
//                int lendaId = rs.getInt("lenda_id");
//                int periodaId = rs.getInt("perioda_id");
//                String dataMungeses = rs.getString("data_mungeses");
//                String arsyeja = rs.getString("arsyeja");
//
//                Mungesa mungesa = Mungesa.getInstance(id, student, lendaId, periodaId, dataMungeses, arsyeja);
//                list.add(mungesa);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        return list;
//    }
//
//    public static Mungesa getById(int id) {
//        String sql = "SELECT * FROM Mungesa WHERE id = ?";
//
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                String student = rs.getString("student");
//                int lendaId = rs.getInt("lenda_id");
//                int periodaId = rs.getInt("perioda_id");
//                String dataMungeses = rs.getString("data_mungeses");
//                String arsyeja = rs.getString("arsyeja");
//
//                return Mungesa.getInstance(id, student, lendaId, periodaId, dataMungeses, arsyeja);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public static boolean create(CreateMungesa mungesa) {
//        String sql = "INSERT INTO Mungesa (student, lenda_id, perioda_id, data_mungeses, arsyeja) VALUES (?, ?, ?, ?, ?)";
//
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, mungesa.getStudent());
//            stmt.setInt(2, mungesa.getLendaId());
//            stmt.setInt(3, mungesa.getPeriodaId());
//            stmt.setString(4, mungesa.getDataMungeses());
//            stmt.setString(5, mungesa.getArsyeja());
//
//            return stmt.executeUpdate() > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//    public static boolean update(UpdateMungesa mungesa) {
//        String sql = "UPDATE Mungesa SET student = ?, lenda_id = ?, perioda_id = ?, data_mungeses = ?, arsyeja = ? WHERE id = ?";
//
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, mungesa.getStudent());
//            stmt.setInt(2, mungesa.getLendaId());
//            stmt.setInt(3, mungesa.getPeriodaId());
//            stmt.setString(4, mungesa.getDataMungeses());
//            stmt.setString(5, mungesa.getArsyeja());
//            stmt.setInt(6, mungesa.getId());
//
//            return stmt.executeUpdate() > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//    public static boolean delete(DeleteMungesa mungesa) {
//        String sql = "DELETE FROM Mungesa WHERE id = ?";
//
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(1, mungesa.getId());
//
//            return stmt.executeUpdate() > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//}
//
//
