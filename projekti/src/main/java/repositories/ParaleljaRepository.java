//package repositories;
//
//import database.DBConnector;
//import models.Paralelja;
//import main.java.models.dto.create.CreateParalelja;
//import main.java.models.dto.update.UpdateParalelja;
//import main.java.models.dto.delete.DeleteParalelja;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ParaleljaRepository {
//
//    private final Connection connection;
//
//    public ParaleljaRepository() throws SQLException {
//        this.connection = DBConnector.getConnection();
//    }
//
//    public List<Paralelja> getAll() throws SQLException {
//        List<Paralelja> lista = new ArrayList<>();
//        String query = "SELECT * FROM paralelja";
//        PreparedStatement stmt = connection.prepareStatement(query);
//        ResultSet result = stmt.executeQuery();
//        while (result.next()) {
//            Paralelja paralelja = Paralelja.getInstance(result);
//            lista.add(paralelja);
//        }
//        return lista;
//    }
//
//    public void create(CreateParalelja dto) throws SQLException {
//        String query = "INSERT INTO paralelja (id, emri) VALUES (?, ?)";
//        PreparedStatement stmt = connection.prepareStatement(query);
//        stmt.setInt(1, dto.getId());
//        stmt.setString(2, dto.getEmri());
//        stmt.executeUpdate();
//    }
//
//    public void update(UpdateParalelja dto) throws SQLException {
//        String query = "UPDATE paralelja SET emri = ? WHERE id = ?";
//        PreparedStatement stmt = connection.prepareStatement(query);
//        stmt.setString(1, dto.getEmri());
//        stmt.setInt(2, dto.getId());
//        stmt.executeUpdate();
//    }
//
//    public void delete(DeleteParalelja dto) throws SQLException {
//        String query = "DELETE FROM paralelja WHERE id = ?";
//        PreparedStatement stmt = connection.prepareStatement(query);
//        stmt.setInt(1, dto.getId());
//        stmt.executeUpdate();
//    }
//}
//
