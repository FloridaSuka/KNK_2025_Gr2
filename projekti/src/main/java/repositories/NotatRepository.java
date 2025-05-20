//package repositories;
//
//import database.DBConnector;
//import models.Notat;
//import models.dto.create.CreateNotat;
//import models.dto.update.UpdateNota;
//import models.dto.delete.DeleteNota;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class NotatRepository {
//
//    public static ArrayList<Notat> getAll() {
//        ArrayList<Notat> list = new ArrayList<>();
//        String sql = "SELECT * FROM Notat";
//
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                int nxenesiId = rs.getInt("nxenesi_id");
//                int lendaId = rs.getInt("lenda_id");
//                int punonjesiId = rs.getInt("punonjesi_id");
//                int drejtimiId = rs.getInt("drejtimi_id");
//                int klasaId = rs.getInt("klasa_id");
//                int paraleljaId = rs.getInt("paralelja_id");
//                int notaPare = rs.getInt("nota_pare");
//                int notaDyte = rs.getInt("nota_dyte");
//
//                Notat nota = new Notat(id, nxenesiId, lendaId, punonjesiId, drejtimiId, klasaId, paraleljaId, notaPare, notaDyte);
//                list.add(nota);
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
//    public static Notat getById(int id) {
//        String sql = "SELECT * FROM Notat WHERE id = ?";
//
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                int nxenesiId = rs.getInt("nxenesi_id");
//                int lendaId = rs.getInt("lenda_id");
//                int punonjesiId = rs.getInt("punonjesi_id");
//                int drejtimiId = rs.getInt("drejtimi_id");
//                int klasaId = rs.getInt("klasa_id");
//                int paraleljaId = rs.getInt("paralelja_id");
//                int notaPare = rs.getInt("nota_pare");
//                int notaDyte = rs.getInt("nota_dyte");
//
//                return new Notat(id, nxenesiId, lendaId, punonjesiId, drejtimiId, klasaId, paraleljaId, notaPare, notaDyte);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public static boolean create(CreateNotat notat) {
//        String sql = "INSERT INTO Notat (nxenesi_id, lenda_id, punonjesi_id, drejtimi_id, klasa_id, paralelja_id, nota_pare, nota_dyte) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(1, notat.getNxenesiId());
//            stmt.setInt(2, notat.getLendaId());
//            stmt.setInt(3, notat.getPunonjesiId());
//            stmt.setInt(4, notat.getDrejtimiId());
//            stmt.setInt(5, notat.getKlasaId());
//            stmt.setInt(6, notat.getParaleljaId());
//            stmt.setInt(7, notat.getNotaPare());
//            stmt.setInt(8, notat.getNotaDyte());
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
//    public static boolean update(UpdateNota nota) {
//        String sql = "UPDATE Notat SET nxenesi_id = ?, lenda_id = ?, punonjesi_id = ?, drejtimi_id = ?, klasa_id = ?, paralelja_id = ?, " +
//                "nota_pare = ?, nota_dyte = ? WHERE id = ?";
//
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(1, nota.getNxenesiId());
//            stmt.setInt(2, nota.getLendaId());
//            stmt.setInt(3, nota.getPunonjesiId());
//            stmt.setInt(4, nota.getDrejtimiId());
//            stmt.setInt(5, nota.getKlasaId());
//            stmt.setInt(6, nota.getParaleljaId());
//            stmt.setInt(7, nota.getNotaPare());
//            stmt.setInt(8, nota.getNotaDyte());
//            stmt.setInt(9, nota.getId());
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
//    public static boolean delete(DeleteNota nota) {
//        String sql = "DELETE FROM Notat WHERE id = ?";
//
//        try (Connection conn = DBConnector.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(1, nota.getId());
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
