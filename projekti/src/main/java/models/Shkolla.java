package models;

import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Shkolla {
    private int id;
    private String emri;
    private String tel;
    private Adresa adresa_id;

    private Shkolla(int id,String emri,String tel,String adresa_id) {
        this.id = id;
        this.emri = emri;
        this.tel = tel;
        this.adresa_id = adresa_id;

    }

    public static Shkolla getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String emri = result.getString("emri");
        String tel = result.getString("tel");
        String adresa_id = result.getString("adresa_id");


        return new Shkolla(id,emri,tel,adresa_id);
    }

    public int getId() {
        return id;
    }

    public String getEmri() {
        return emri;
    }
    public String getTel() {
        return tel;
    }
    public String getAdresaId() {
        return adresa_id;
    }


}