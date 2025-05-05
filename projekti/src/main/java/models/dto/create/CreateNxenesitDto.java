package models.dto.create;
import java.util.Date;

public class CreateNxenesitDto {
    private int ID;
    private String emri;
    private String mbiemri;
    private Date datelindja;
    private char gjinia;
    private String email;
    private String phone;
    private int adresaID;

    public CreateNxenesitDto(int ID, String emri, String mbiemri, Date datelindja,
                             char gjinia, String email, String phone, int adresaID) {
        this.ID = ID;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.datelindja = datelindja;
        this.gjinia = gjinia;
        this.email = email;
        this.phone = phone;
        this.adresaID = adresaID;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID){
        this.ID=ID;
    }

    public String getEmri() {
        return this.emri;
    }

    public void setEmri(String emri){
        this.emri=emri;
    }

    public String getMbiemri() {
        return this.mbiemri;
    }

    public void setMbiemri(String mbiemri){
        this.mbiemri=mbiemri;
    }

    public Date getDatelindja() {
        return this.datelindja;
    }

    public void setDatelindja(Date datelindja){
        this.datelindja=datelindja;
    }

    public char getGjinia() {
        return this.gjinia;
    }

    public void setGjinia(char gjinia){
        this.gjinia=gjinia;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }

    public int getAdresa() {
        return this.adresaID;
    }

    public void setAdresa(int adresaID){
        this.adresaID=adresaID;
    }
}