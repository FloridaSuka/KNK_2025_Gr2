package models;


import java.sql.ResultSet;
import java.sql.SQLException;

public class Punonjesit {
    private int id;
    private String emri;
    private String mbiemri;
    private String email;
    private String tel;
    private String roli;
    private Adresa adresa_id;

    private Punonjesit(int id,String emri, String mbiemri,String email,String tel,String roli,String adresa_id) {
      this.id = id;
      this.emri = emri;
      this.mbiemri = mbiemri;
      this.email = email;
      this.tel = tel;
      this.roli = roli;
      this.adresa_id = adresa_id;

    }

    public static Punonjesit getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String emri = result.getString("emri");
        String mbiemri = result.getString("mbiemri");
        String email = result.getString("email");
        String tel = result.getString("tel");
        String roli = result.getString("roli");
        String adresa_id = result.getString("adresa_id");


        return new Punonjesit(id,emri,mbiemri,email,tel,roli,adresa_id);
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
    public String getAdresaId() {
        return adresa_id;
    }


}