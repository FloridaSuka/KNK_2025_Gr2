package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Nxenesit {
    private int id;
    private String emri;
    private String mbiemri;
    private Date datelindja;
    private char gjinia;
    private String email;
    private String phone;
    private Adresa adresa;


    private Nxenesit(int id, String emri, String mbiemri, Date datelindja, char gjinia, String email, String phone, Adresa adresa) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.datelindja = datelindja;
        this.gjinia = gjinia;
        this.email = email;
        this.phone = phone;
        this.adresa = adresa;
    }

    public static Nxenesit getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String emri = result.getString("emri");
        String mbiemri=result.getString("mbiemri");
        Date datelindja = result.getDate("datelindja");
        char gjinia = result.getString("gjinia").charAt(0);
        String email = result.getString("email");
        String phone = result.getString("phone");
        Adresa adresa = Adresa.getInstance(result);
        return new Nxenesit(id, emri, mbiemri, datelindja, gjinia, email, phone, adresa);
    }

    public int getId() {
        return id;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public Date getDatelindja() {
        return datelindja;
    }

    public char getGjinia() {
        return gjinia;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Adresa getAdresa() {
        return adresa;
    }
}