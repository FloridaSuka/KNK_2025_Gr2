package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static Connection connection = null;
    private static final String DB_URL = "jdbc:postgresql://localhost/knk_2025";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1234";
    public static Connection getConnection(){
        if(connection == null){
            try{
                connection=DriverManager.getConnection(
                        DB_URL,
                        DB_USER,
                        DB_PASSWORD
                );
                System.out.println("Lidhja u krijua me sukses!");
            }catch(SQLException e){
                System.out.println("Gabim lidhje: " + e.getMessage());
                return null;
            }
        }
        return connection;
    }
}
