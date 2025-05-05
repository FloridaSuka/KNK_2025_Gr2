package models.dto.create;

public class CreateLenda {
    private String emri;
    private int idMesimi;
    private int idDrejtimi;
    private int idPerioda;
    private int idMesuesi;

    public CreateLenda(String emri, int idMesimi, int idDrejtimi, int idPerioda, int idMesuesi) {
        this.emri = emri;
        this.idMesimi = idMesimi;
        this.idDrejtimi = idDrejtimi;
        this.idPerioda = idPerioda;
        this.idMesuesi = idMesuesi;
    }

    public String getEmri() { return emri; }
    public int getIdMesimi() { return idMesimi; }
    public int getIdDrejtimi() { return idDrejtimi; }
    public int getIdPerioda() { return idPerioda; }
    public int getIdMesuesi() { return idMesuesi; }

    public void setEmri(String emri) { this.emri = emri; }
    public void setIdMesimi(int idMesimi) { this.idMesimi = idMesimi; }
    public void setIdDrejtimi(int idDrejtimi) { this.idDrejtimi = idDrejtimi; }
    public void setIdPerioda(int idPerioda) { this.idPerioda = idPerioda; }
    public void setIdMesuesi(int idMesuesi) { this.idMesuesi = idMesuesi; }
}
