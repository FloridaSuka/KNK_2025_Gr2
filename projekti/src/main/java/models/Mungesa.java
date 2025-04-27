package models;

public class Mungesa {
    private int id;
    private String student;
    private int lendaId;
    private int periodaId;
    private String dataMungeses;
    private String arsyeja;

    public Mungesa(int id, String student, int lendaId, int periodaId, String dataMungeses, String arsyeja) {
        this.id = id;
        this.student = student;
        this.lendaId = lendaId;
        this.periodaId = periodaId;
        this.dataMungeses = dataMungeses;
        this.arsyeja = arsyeja;
    }

    public int getId() { return id; }
    public String getStudent() { return student; }
    public int getLendaId() { return lendaId; }
    public int getPeriodaId() { return periodaId; }
    public String getDataMungeses() { return dataMungeses; }
    public String getArsyeja() { return arsyeja; }
}
