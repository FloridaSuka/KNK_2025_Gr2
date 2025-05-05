package models.dto.create;

public class CreateMungesa {
    private String student;
    private int lendaId;
    private int periodaId;
    private String dataMungeses;
    private String arsyeja;

    public CreateMungesa(String student, int lendaId, int periodaId, String dataMungeses, String arsyeja) {
        this.student = student;
        this.lendaId = lendaId;
        this.periodaId = periodaId;
        this.dataMungeses = dataMungeses;
        this.arsyeja = arsyeja;
    }

    public String getStudent() { return student; }
    public int getLendaId() { return lendaId; }
    public int getPeriodaId() { return periodaId; }
    public String getDataMungeses() { return dataMungeses; }
    public String getArsyeja() { return arsyeja; }

    public void setStudent(String student) { this.student = student; }
    public void setLendaId(int lendaId) { this.lendaId = lendaId; }
    public void setPeriodaId(int periodaId) { this.periodaId = periodaId; }
    public void setDataMungeses(String dataMungeses) { this.dataMungeses = dataMungeses; }
    public void setArsyeja(String arsyeja) { this.arsyeja = arsyeja; }
}
