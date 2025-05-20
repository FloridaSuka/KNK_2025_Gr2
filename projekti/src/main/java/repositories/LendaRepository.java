package repositories;

import database.DBConnector;
import java.sql.*;

public class LendaRepository {
    public int getLendaIdByName(String emri) {
        String sql = "SELECT id FROM lenda WHERE LOWER(emri) = LOWER(?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, emri.trim());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // ose -1 nëse s’gjendet
    }
}
