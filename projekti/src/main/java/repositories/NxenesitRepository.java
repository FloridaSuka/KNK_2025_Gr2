//package repositories;
//
//import database.DBConnector;
//import models.Nxenesit;
//import models.dto.create.CreateNxenesit;
//import models.dto.update.UpdateNxenesi;
//import models.dto.delete.DeleteNxenesi;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class NxenesitRepository {
//
//    private final Connection connection;
//
//    public NxenesitRepository() throws SQLException {
//        this.connection = DBConnector.getConnection();
//    }
//
//    public List<Nxenesit> getAll() throws SQLException {
//        List<Nxenesit> nxenesitList = new ArrayList<>();
//        String query = "SELECT * FROM nxenesit n " +
//                "JOIN adresa a ON n.adresa_id = a.id";
//        PreparedStatement stmt = connection.prepareStatement(query);
//        ResultSet result = stmt.executeQuery();
//        while (result.next()) {
//            Nxenesit nxenesi = Nxenesit.getInstance(result);
//            nxenesitList.add(nxenesi);
//        }
//        return nxenesitList;
//    }
//
//    public void create(CreateNxenesit dto) throws SQLException {
//        String query = "INSERT INTO nxenesit (id, emri, mbiemri, datelindja, gjinia, email, phone, adresa_id) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        PreparedStatement stmt = connection.prepareStatement(query);
//        stmt.setInt(1, dto.getID());
//        stmt.setString(2, dto.getEmri());
//        stmt.setString(3, dto.getMbiemri());
//        stmt.setDate(4, new java.sql.Date(dto.getDatelindja().getTime()));
//        stmt.setString(5, String.valueOf(dto.getGjinia()));
//        stmt.setString(6, dto.getEmail());
//        stmt.setString(7, dto.getPhone());
//        stmt.setInt(8, dto.getAdresa().getId()); // assuming Adresa has getId()
//        stmt.executeUpdate();
//    }
//
//    public void update(UpdateNxenesi dto) throws SQLException {
//        String query = "UPDATE nxenesit SET emri = ?, mbiemri = ?, datelindja = ?, gjinia = ?, " +
//                "email = ?, phone = ?, adresa_id = ? WHERE id = ?";
//        PreparedStatement stmt = connection.prepareStatement(query);
//        stmt.setString(1, dto.getEmri());
//        stmt.setString(2, dto.getMbiemri());
//        stmt.setDate(3, dto.getDatelindja());
//        stmt.setString(4, String.valueOf(dto.getGjinia()));
//        stmt.setString(5, dto.getEmail());
//        stmt.setString(6, dto.getPhone());
//        stmt.setInt(7, dto.getAdresa().getId());
//        stmt.setInt(8, dto.getId());
//        stmt.executeUpdate();
//    }
//
//    public void delete(DeleteNxenesi dto) throws SQLException {
//        String query = "DELETE FROM nxenesit WHERE id = ?";
//        PreparedStatement stmt = connection.prepareStatement(query);
//        stmt.setInt(1, dto.getId());
//        stmt.executeUpdate();
//    }
//}
