package models;

public class Lenda {
    private int id;     // nga jashtë
    private String emri;     // e kësaj klase
    private int idMesimi;    // nga tabela Mesimi (Diella)
    private int idDrejtimi;  // nga tabela Drejtimi (Diella)
    private int idPerioda;   // nga tabela Perioda (Gresa)
    private int idMesuesi;   // nga tabela User ose Punonjesit

    public Lenda(int idLenda, String emri, int idMesimi, int idDrejtimi, int idPerioda, int idMesuesi) {
        this.id = id;
        this.emri = emri;
        this.idMesimi = idMesimi;
        this.idDrejtimi = idDrejtimi;
        this.idPerioda = idPerioda;
        this.idMesuesi = idMesuesi;
    }

    public int getIdLenda() {
        return id;
    }

    public void setIdLenda(int idLenda) {
        this.id = idLenda;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public int getIdMesimi() {
        return idMesimi;
    }

    public void setIdMesimi(int idMesimi) {
        this.idMesimi = idMesimi;
    }

    public int getIdDrejtimi() {
        return idDrejtimi;
    }

    public void setIdDrejtimi(int idDrejtimi) {
        this.idDrejtimi = idDrejtimi;
    }

    public int getIdPerioda() {
        return idPerioda;
    }

    public void setIdPerioda(int idPerioda) {
        this.idPerioda = idPerioda;
    }

    public int getIdMesuesi() {
        return idMesuesi;
    }

    public void setIdMesuesi(int idMesuesi) {
        this.idMesuesi = idMesuesi;
    }

    @Override
    public String toString() {
        return "Lenda{" +
                "idLenda=" + id +
                ", emri='" + emri + '\'' +
                ", idMesimi=" + idMesimi +
                ", idDrejtimi=" + idDrejtimi +
                ", idPerioda=" + idPerioda +
                ", idMesuesi=" + idMesuesi +
                '}';
    }
}
