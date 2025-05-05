package main.java.models;

import models.Adresa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Nxenesit {
    private int id;
    private String emri;
    private String mbiemri;
    private Date datelindja;
    private boolean gjinia;
    private String email;
    private String phone;
    private Adresa adresa;


    public Nxenesit(int id, String emri, String mbiemri, Date datelindja, boolean gjinia, String email, String phone, String adresa) {
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
        boolean gjinia = result.getBoolean("gjinia");
        String email = result.getString("email");
        String phone = result.getString("phone");
        String adresa = result.getString("adresa");
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

    public boolean isGjinia() {
        return gjinia;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdresa() {
        return adresa;
    }
}