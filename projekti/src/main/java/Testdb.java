import java.sql.Connection;
import database.DBConnector;

public class Testdb {
    public static void main(String[] args) {
        Connection conn = DBConnector.getConnection();
        if (conn != null) {
            System.out.println("✅ Lidhja me databazën u realizua me sukses.");
        } else {
            System.out.println("❌ Lidhja me databazën dështoi.");
        }
    }
}
