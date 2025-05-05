package models;

import models.Adresa;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Shkolla {
    private int id;
    private String emri;
    private String tel;
    private int adresaId;

    private Shkolla(int id,String emri,String tel,int adresaId) {
        this.id = id;
        this.emri = emri;
        this.tel = tel;
        this.adresaId = adresaId;
    }

    public static Shkolla getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String emri = result.getString("emri");
        String tel = result.getString("tel");
        int adresaId = result.getInt("adresaId");

        return new Shkolla(id,emri,tel,adresaId);
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

    public int getAdresaId() {
        return adresaId;
    }
}
