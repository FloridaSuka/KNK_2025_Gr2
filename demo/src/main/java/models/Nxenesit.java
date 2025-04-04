package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Nxenesit {
    private int id;
    private String name;
    private String surname;
    private String email;
    private int age;
    private char  gjinia[];
    private String adresa[];
    private String phone;

    private User(int id, String name, String email, String surname int age, String[] adresa, String phone, char[]  gjinia) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.mosha = mosha;
        this.gjinia = gjinia;
        this.adresa = adresa;
        this.phone = phone;
    }

    public static User getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String emri = result.getString("emri");
        String mbiemri=result.getString("mbiemri");
        String email = result.getString("email");
        int mosha = result.getInt("mosha");
        String adresa[]
        return new User(id, name, email, age);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}