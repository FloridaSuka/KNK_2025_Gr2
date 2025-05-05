package main.java.models.dto.delete;
import models.Adresa;

public class DeleteShkollaDto {
    private int id;
    private String emri;
    private String tel;
    private Adresa adresa;

    public DeleteShkollaDto(int id, String emri, String tel, Adresa adresa) {
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
