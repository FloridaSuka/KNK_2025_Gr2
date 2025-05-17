package models;

public class Lenda {
    private int id;
    private String emri;
    private int idMesimi;
    private int idDrejtimi;
    private int idPerioda;
    private int idMesuesi;

    // Konstruktor privat
    public Lenda(int idLenda, String emri, int idMesimi, int idDrejtimi, int idPerioda, int idMesuesi) {
        this.id = idLenda;
        this.emri = emri;
        this.idMesimi = idMesimi;
        this.idDrejtimi = idDrejtimi;
        this.idPerioda = idPerioda;
        this.idMesuesi = idMesuesi;
    }

    // Metodë statike për krijim të kontrolluar
    public static Lenda of(int idLenda, String emri, int idMesimi, int idDrejtimi, int idPerioda, int idMesuesi) {
        return new Lenda(idLenda, emri, idMesimi, idDrejtimi, idPerioda, idMesuesi);
    }

    // Getters & Setters
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

    // Format për model logjik (KNK - ERD)
    public void printERDFormat() {
        System.out.println("Tabela: Lenda");
        System.out.println("PK → idLenda: " + id);
        System.out.println("emri: " + emri);
        System.out.println("FK → idMesimi (Mesimi): " + idMesimi);
        System.out.println("FK → idDrejtimi (Drejtimi): " + idDrejtimi);
        System.out.println("FK → idPerioda (Perioda): " + idPerioda);
        System.out.println("FK → idMesuesi (Punonjesit): " + idMesuesi);
    }

    // Format për model fizik (SQL)
    public String toInsertSQL() {
        return "INSERT INTO Lenda (id, emri, mesimi_id, drejtimi_id, perioda_id, mesuesi_id) VALUES (" +
                id + ", '" + emri + "', " + idMesimi + ", " + idDrejtimi + ", " + idPerioda + ", " + idMesuesi + ");";
    }

    // Format për kontroll të normalizimit (relacione)
    public String toNormalizedRecord() {
        return "[Lenda: id=" + id + ", emri=" + emri + "] → " +
                "(Mesimi#" + idMesimi + ", Drejtimi#" + idDrejtimi + ", Perioda#" + idPerioda + ", Mesuesi#" + idMesuesi + ")";
    }

    // Zëvendëson toString() default
    @Override
    public String toString() {
        return toNormalizedRecord(); // Mund ta ndrosh në printERDFormat() ose toInsertSQL() nëse preferon
    }
}
