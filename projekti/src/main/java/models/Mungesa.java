package models;

public class Mungesa {
    private int id;
    private String student;
    private int lendaId;
    private int periodaId;
    private String dataMungeses;
    private String arsyeja;

    // Konstruktor privat – kontroll i instancimit
    private Mungesa(int id, String student, int lendaId, int periodaId, String dataMungeses, String arsyeja) {
        this.id = id;
        this.student = student;
        this.lendaId = lendaId;
        this.periodaId = periodaId;
        this.dataMungeses = dataMungeses;
        this.arsyeja = arsyeja;
    }

    // Metodë statike për krijim të kontrolluar
    public static Mungesa of(int id, String student, int lendaId, int periodaId, String dataMungeses, String arsyeja) {
        return new Mungesa(id, student, lendaId, periodaId, dataMungeses, arsyeja);
    }

    // Getters
    public int getId() { return id; }
    public String getStudent() { return student; }
    public int getLendaId() { return lendaId; }
    public int getPeriodaId() { return periodaId; }
    public String getDataMungeses() { return dataMungeses; }
    public String getArsyeja() { return arsyeja; }

    // 🔍 Modeli logjik (ERD)
    public void printERDFormat() {
        System.out.println("Tabela: Mungesa");
        System.out.println("PK → id: " + id);
        System.out.println("student: " + student);
        System.out.println("FK → lendaId (Lenda): " + lendaId);
        System.out.println("FK → periodaId (Perioda): " + periodaId);
        System.out.println("dataMungeses: " + dataMungeses);
        System.out.println("arsyeja: " + arsyeja);
    }

    // 🔄 Modeli fizik – SQL INSERT
    public String toInsertSQL() {
        return "INSERT INTO Mungesa (id, student, lenda_id, perioda_id, data_mungeses, arsyeja) VALUES (" +
                id + ", '" + student + "', " + lendaId + ", " + periodaId + ", '" + dataMungeses + "', '" + arsyeja + "');";
    }

    // 🔁 Normalizimi – përfaqësim lidhjesh me tabela tjera
    public String toNormalizedRecord() {
        return "[Mungesa: id=" + id + ", student=" + student + "] → " +
                "(Lenda#" + lendaId + ", Perioda#" + periodaId + ", data=" + dataMungeses + ", arsyeja=" + arsyeja + ")";
    }

    // Përdor këtë për përfaqësim të lehtë
    @Override
    public String toString() {
        return toNormalizedRecord(); // Mund ta ndryshosh në toInsertSQL() ose printERDFormat()
    }
}
