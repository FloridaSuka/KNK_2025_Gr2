//package services;
//
//import database.DBConnector;
//import models.Mungesa;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MungesaService {
//    public List<Mungesa> getMungesat() {
//        List<Mungesa> lista = new ArrayList<>();
//        String query = "SELECT * FROM mungesat";
//
//        try (Connection conn = DBConnector.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(query)) {
//
//            while (rs.next()) {
//                lista.add(Mungesa.getInstance(
//                        rs.getInt("id"),
//                        rs.getString("student"),
//                        rs.getInt("lenda_id"),
//                        rs.getInt("perioda_id"),
//                        rs.getString("data_mungeses"),
//                        rs.getString("arsyeja")
//                ));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return lista;
//    }
//}
