package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Punonjesit {
    private int punonjesit_id;
    private String punonjesit_emri;
    private String punonjesit_mbiemri;
    private String punonjesit_email;
    private String punonjesit_tel;
    private String roli;
    private String punonjesit_qyteti;

    private Punonjesit(int punonjesit_id,String punonjesit_emri, String punonjesit_mbiemri,String punonjesit_email,String punonjesit_tel,String roli,String punonjesit_qyteti) {
      this.punonjesit_id = punonjesit_id;
      this.punonjesit_emri = punonjesit_emri;
      this.punonjesit_mbiemri = punonjesit_mbiemri;
      this.punonjesit_email = punonjesit_email;
      this.punonjesit_tel = punonjesit_tel;
      this.roli = roli;
      this.punonjesit_qyteti
    }

    public static Punonjesit getInstance(ResultSet result) throws SQLException {
        int punonjesitId = result.getInt("punonjesit_id");
        String punonjesitEmri= result.getString("punonjesit_emri");
        String punonjesitMbiemri = result.getString("punonjesit_mbiemri");
        String punonjesitEmail = result.getString("punonjesit_email");
        String punonjesitTel = result.getString("punonjesit_tel");
        String roli = result.getString("roli");
        Strinf punonjesitQyteti = result.getString("punonjesit_qyteti");


        return new Punonjesit(punonjesitId,punonjesitEmri,punonjesitMbiemri,punonjesitEmail,punonjesitTel,roli,punonjesitQyteti);
    }

    public int getId() {
        return punonjesit_id;
    }

    public String getEmri() {
        return punonjesit_emri;
    }

    public String getMbiemri() {
        return punonjesit_mbiemri;
    }
    public String getEmail() {
        return punonjesit_email;
    }
    public String getTel() {
        return punonjesit_tel;
    }
    public String getRoli() {
        return roli;
    }
    public String getQyteti() {
        return punonjesit_qyteti;
    }


}