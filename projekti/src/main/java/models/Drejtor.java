package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Drejtor {
    private int id;
    private String emri;
    private String mbiemri;
    private String email;
    private String tel;
    private String roli;
    private Adresa adresa;

    private Drejtor(int id, String emri, String mbiemri, String email, String tel, String roli, Adresa adresa) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.tel = tel;
        this.roli = roli;
        this.adresa = adresa;

    }

    public static Drejtor getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String emri = result.getString("emri");
        String mbiemri = result.getString("mbiemri");
        String email = result.getString("email");
        String tel = result.getString("tel");
        String roli = result.getString("roli");
        Adresa adresa = Adresa.getInstance(result);

        return new Drejtor(id,emri,mbiemri,email,tel,roli,adresa);
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

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getRoli() {
        return roli;
    }

    public Adresa getAdresa() {
        return adresa;
    }
}