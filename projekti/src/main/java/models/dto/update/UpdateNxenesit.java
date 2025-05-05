package main.java.models.dto.update;
import java.sql.Date;

public class UpdateNxenesit {
    private int id;
    private String emri;
    private String mbiemri;
    private Date datelindja;
    private boolean gjinia;
    private String email;
    private String phone;
    private String adresa;

    public UpdateNxenesit(int id, String emri, String mbiemri, Date datelindja,
                          boolean gjinia, String email, String phone, String adresa) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.datelindja = datelindja;
        this.gjinia = gjinia;
        this.email = email;
        this.phone = phone;
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

    public Date getDatelindja() {
        return datelindja;
    }

    public boolean isGjinia() {
        return gjinia;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdresa() {
        return adresa;
    }
}
