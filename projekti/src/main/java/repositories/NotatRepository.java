package repositories;

import database.DBConnector;
import models.Notat;
import models.dto.create.CreateNotat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotatRepository {
    public boolean regjistroNota(CreateNotat nota) {
        String query = "INSERT INTO Notat (nxenesi_id, lenda_id, mesuesi_id, drejtimi_id, klasa_id, paralelja_id,periudha_id, nota_pare, nota_dyte) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, nota.nxenesiId);
            stmt.setInt(2, nota.lendaId);
            stmt.setInt(3, nota.mesuesiId);
            stmt.setInt(4, nota.drejtimiId);
            stmt.setInt(5, nota.klasaId);
            stmt.setInt(6, nota.paraleljaId);
            stmt.setInt(7,nota.periudhaId);
            stmt.setInt(8, nota.notaPare);
            stmt.setInt(9, nota.notaDyte);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int numronNotat(int nota) {
        String query = "SELECT COUNT(*) AS total FROM Notat WHERE nota_pare = ?";
        int total = 0;

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, nota);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }
    public List<Notat> gjejTeGjithaNotat() {
        List<Notat> lista = new ArrayList<>();
        String query = "SELECT * FROM Notat";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Notat n = Notat.fromResultSet(rs);
                lista.add(n);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }



}
