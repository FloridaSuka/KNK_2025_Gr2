//package services;
//
//import database.DBConnector;
//import models.Mesimi;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MesimiService {
//
//    public List<Mesimi> getMesimet() {
//        List<Mesimi> mesimet = new ArrayList<>();
//
//        try (Connection connection = DBConnector.getConnection();
//             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Mesimi");
//             ResultSet resultSet = statement.executeQuery()) {
//
//            while (resultSet.next()) {
//                Mesimi mesimi = Mesimi.getInstance(resultSet);
//                mesimet.add(mesimi);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return mesimet;
//    }
//
//    public void addMesimi(Mesimi mesimi) {
//        try (Connection connection = DBConnector.getConnection();
//             PreparedStatement statement = connection.prepareStatement(
//                     "INSERT INTO Mesimi (lid, pid, klid, did) VALUES (?, ?, ?, ?)")) {
//
//            statement.setInt(1, mesimi.getLendaId());
//            statement.setInt(2, mesimi.getProfesoriId());
//            statement.setInt(3, mesimi.getKlasaId());
//            statement.setInt(4, mesimi.getDrejtimiId());
//
//            statement.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteMesimi(int lendaId, int profesoriId, int klasaId, int drejtimiId) {
//        try (Connection connection = DBConnector.getConnection();
//             PreparedStatement statement = connection.prepareStatement(
//                     "DELETE FROM Mesimi WHERE lid = ? AND pid = ? AND klid = ? AND did = ?")) {
//
//            statement.setInt(1, lendaId);
//            statement.setInt(2, profesoriId);
//            statement.setInt(3, klasaId);
//            statement.setInt(4, drejtimiId);
//
//            statement.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
