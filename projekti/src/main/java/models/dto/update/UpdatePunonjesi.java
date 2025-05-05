package main.java.models.dto.update;
import models.Adresa;


public class UpdatePunonjesi {
    private int id;
    private String emri;
    private String mbiemri;
    private String email;
    private String tel;
    private String roli;
    private Adresa adresa;

    public UpdatePunonjesi(int id, String emri, String mbiemri, String email, String tel, Adresa adresa) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.tel = tel;
        this.adresa = adresa;
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

    public Adresa getAdresa() {
        return adresa;
    }
}
