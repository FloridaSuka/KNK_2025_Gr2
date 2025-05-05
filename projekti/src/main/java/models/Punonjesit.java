package main.java.models;
import models.Adresa;


import java.sql.ResultSet;
import java.sql.SQLException;

public class Punonjesit {
    private int id;
    private String emri;
    private String mbiemri;
    private String email;
    private String tel;
    private String roli;
    private int adresaId;

<<<<<<< Updated upstream
    private Punonjesit(int id,String emri, String mbiemri,String email,String tel,String roli,Adresa adresa_id) {
=======
    private Punonjesit(int id,String emri, String mbiemri,String email,String tel,String roli,int adresaId) {
>>>>>>> Stashed changes
      this.id = id;
      this.emri = emri;
      this.mbiemri = mbiemri;
      this.email = email;
      this.tel = tel;
      this.roli = roli;
      this.adresaId = adresaId;

    }

    public static Punonjesit getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String emri = result.getString("emri");
        String mbiemri = result.getString("mbiemri");
        String email = result.getString("email");
        String tel = result.getString("tel");
        String roli = result.getString("roli");
<<<<<<< Updated upstream
        int adresaId = result.getInt("adresa_id");
        Adresa adresa = Adresa.getInstance(result);


        return new Punonjesit(id,emri,mbiemri,email,tel,roli,adresa);
=======
        int adresaId = result.getInt("adresaId");


        return new Punonjesit(id,emri,mbiemri,email,tel,roli,adresaId);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    public Adresa getAdresaId() {
        return adresa_id;
=======
    public int getAdresaId() {
        return adresaId;
>>>>>>> Stashed changes
    }


}