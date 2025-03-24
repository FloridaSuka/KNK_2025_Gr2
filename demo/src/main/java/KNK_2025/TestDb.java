package KNK_2025;

import Database.DBConnector;
import java.sql.*;

public class TestDb {
    public static void main(String[] args)  throws SQLException {
        Connection connection = DBConnector.getConnection();
        if (connection == null) {
            System.out.println("Lidhja me databazën ka dështuar!");
            return;
        }
        String query = " INSERT INTO users (emri, mbiemri, mosha, email, new_id ) VALUES ('grupi_2','KNK', 25,'gr2test@gmail.com', 100)";
        Statement statement = connection.createStatement();
        statement.execute(query);
//        statement.execute("DELETE FROM users WHERE id = 1");
        query = " SELECT * FROM Users ";
        ResultSet results = statement.executeQuery(query);
        while(results.next()){
            int new_id = results.getInt("new_id");
            String name = results.getString("emri");
            String surname = results.getString("mbiemri");
            int age = results.getInt("mosha");
            String email = results.getString("email");
            System.out.println("ID: " + new_id);
            System.out.println("Name: " + name);
            System.out.println("Mbiemri: " + surname);
            System.out.println("Email: " + email);
            System.out.println("Age: " + age);
            System.out.println("------------------");
        }
        //fshirja e te gjitha rekordeve
        statement.execute("DELETE FROM users");
    }
}
