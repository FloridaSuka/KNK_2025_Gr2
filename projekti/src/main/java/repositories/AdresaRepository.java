package repositories;

import database.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdresaRepository {

    public Integer gjejAdresaId(String emriRruges) {
        String sql = "SELECT id FROM adresa WHERE LOWER(rruga) = LOWER(?)";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emriRruges.trim());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return null; // nuk u gjet
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
