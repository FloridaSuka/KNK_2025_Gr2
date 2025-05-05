package models.dto.create;

public class CreateDrejtimiDTO {
    private String emri;
    private int shkollaId;
    private int paraleljaId;

    public CreateDrejtimiDTO(String emri, int shkollaId, int paraleljaId) {
        this.emri = emri;
        this.shkollaId = shkollaId;
        this.paraleljaId = paraleljaId;
    }

    public String getEmri() {
        return emri;
    }

    public int getShkollaId() {
        return shkollaId;
    }

    public int getParaleljaId() {
        return paraleljaId;
    }
}
