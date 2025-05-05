package main.java.models.dto.update;
import models.Adresa;

public class UpdateShkolla {
    private int id;
    private String emri;
    private String tel;
    private Adresa adresa;

    public UpdateShkolla(int id, String emri, String tel, Adresa adresa) {
        this.id = id;
        this.emri = emri;
        this.tel = tel;
        this.adresa = adresa;
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

    public Adresa getAdresa() {
        return adresa;
    }
}
