package repositories;

import database.DBConnector;
import models.dto.create.CreateNotat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotatRepository {
    public boolean regjistroNota(CreateNotat nota) {
        String query = "INSERT INTO Notat (nxenesi_id, lenda_id, mesuesi_id, drejtimi_id, klasa_id, paralelja_id, nota_pare, nota_dyte) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, nota.nxenesiId);
            stmt.setInt(2, nota.lendaId);
            stmt.setInt(3, nota.mesuesiId);
            stmt.setInt(4, nota.drejtimiId);
            stmt.setInt(5, nota.klasaId);
            stmt.setInt(6, nota.paraleljaId);
            stmt.setInt(7, nota.notaPare);
            stmt.setInt(8, nota.notaDyte);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
