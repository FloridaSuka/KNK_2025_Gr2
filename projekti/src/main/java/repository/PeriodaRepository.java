package repository;

import database.DBConnector;
import models.Perioda;
import models.dto.create.CreatePerioda;
import models.dto.update.UpdatePerioda;
import models.dto.delete.DeletePerioda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeriodaRepository {

    private final Connection connection;

    public PeriodaRepository() throws SQLException {
        this.connection = DBConnector.getConnection();
    }

    public List<Perioda> getAll() throws SQLException {
        List<Perioda> periodat = new ArrayList<>();
        String query = "SELECT * FROM perioda";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String emri = rs.getString("emri");
            String dataFillimit = rs.getString("dataFillimit");
            String dataMbarimit = rs.getString("dataMbarimit");
            Perioda perioda = Perioda.of(id, emri, dataFillimit, dataMbarimit);
            periodat.add(perioda);
        }

        return periodat;
    }

    public void create(CreatePerioda dto) throws SQLException {
        String query = "INSERT INTO perioda (emri, dataFillimit, dataMbarimit) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, dto.getEmri());
        stmt.setString(2, dto.getDataFillimit());
        stmt.setString(3, dto.getDataMbarimit());
        stmt.executeUpdate();
    }

    public void update(UpdatePerioda dto) throws SQLException {
        String query = "UPDATE perioda SET emri = ?, dataFillimit = ?, dataMbarimit = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, dto.getEmri());
        stmt.setString(2, dto.getDataFillimit());
        stmt.setString(3, dto.getDataMbarimit());
        stmt.setInt(4, dto.getIdPerioda());
        stmt.executeUpdate();
    }

    public void delete(DeletePerioda dto) throws SQLException {
        String query = "DELETE FROM perioda WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, dto.getId());
        stmt.executeUpdate();
    }
}

