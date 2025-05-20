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
    private Shkolla shkolla;

    public Drejtor(int id, String emri, String mbiemri, String email, String tel, String roli, Adresa adresa, Shkolla shkolla) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.tel = tel;
        this.roli = roli;
        this.adresa = adresa;
        this.shkolla = shkolla;
    }

    public Drejtor(String emri, String mbiemri, String email, String tel, String roli, Adresa adresa, Shkolla shkolla) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.tel = tel;
        this.roli = roli;
        this.adresa = adresa;
        this.shkolla = shkolla;
    }

    public static Drejtor fromResultSet(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String emri = result.getString("emri");
        String mbiemri = result.getString("mbiemri");
        String email = result.getString("email");
        String tel = result.getString("tel");
        String roli = result.getString("roli");

        Adresa adresa = Adresa.getInstance(result);
        Shkolla shkolla = Shkolla.fromResultSet(result);

        return new Drejtor(id, emri, mbiemri, email, tel, roli, adresa, shkolla);
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

    public Shkolla getShkolla() {
        return shkolla;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setRoli(String roli) {
        this.roli = roli;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public void setShkolla(Shkolla shkolla) {
        this.shkolla = shkolla;
    }

    @Override
    public String toString() {
        return "Drejtor {" +
                "ID = " + id +
                ", Emri = '" + emri + '\'' +
                ", Mbiemri = '" + mbiemri + '\'' +
                ", Email = '" + email + '\'' +
                ", Tel = '" + tel + '\'' +
                ", Roli = '" + roli + '\'' +
                ", Adresa = " + adresa +
                ", Shkolla = " + shkolla +
                '}';
    }
}
