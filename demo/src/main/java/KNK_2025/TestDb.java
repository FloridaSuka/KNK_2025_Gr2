package KNK_2025;

import Database.DBConnector;
import java.sql.*;

//Krijimi i lidhjes me bazen e te dhenave
//Ekzekutimi i pyetesoreve nga kodi
public class TestDb {
    public static void main(String[] args)  throws SQLException {
//        String url = "jdbc:postgresql://localhost/Knk-2025";
//        String user = "postgres";
//        String password = "leonna123";
//        Connection connection = DriverManager.getConnection(url,user,password);
//        if(connection.isValid(100)){
//            System.out.println("DB connected");
        Connection connection = DBConnector.getConnection();
        String query = " INSERT INTO Users(Id, Emri, Mbiemri, Mosha, Email ) VALUES (401, 'filann','filannii', 25,'fila.n@gmail.com')";
        Statement statement = connection.createStatement();
        //statement.executeQuery();
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
    }
}
