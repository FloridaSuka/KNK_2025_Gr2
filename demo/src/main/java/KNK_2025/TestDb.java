package KNK_2025;

import Database.DBConnector;
import java.sql.*;

public class TestDb {
    public static void main(String[] args)  throws SQLException {
        Connection connection = DBConnector.getConnection();
        String query = " INSERT INTO Users(Id, Emri, Mbiemri, Mosha, Email ) VALUES (101, 'filann','filannii', 25,'fila.n@gmail.com')";
        Statement statement = connection.createStatement();
        statement.execute(query);
        statement.execute("DELETE FROM users WHERE id = 1");
        query = " SELECT * FROM Users ";
        ResultSet results = statement.executeQuery(query);
        while(results.next()){
            int id = results.getInt("Id");
            String name = results.getString("Emri");
            String surname = results.getString("Mbiemri");
            int age = results.getInt("Mosha");
            String email = results.getString("Email");
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Mbiemri: " + surname);
            System.out.println("Email: " + email);
            System.out.println("Age: " + age);
            System.out.println("------------------");
        }
        //fshirja e te gjitha rekordeve
        statement.execute("TRUNCATE TABLE Users RESTART IDENTITY");
    }
}
