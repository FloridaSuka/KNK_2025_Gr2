package main.java.models.dto.delete;

public class DeleteParaleljaDto {
    private int id;
    private String emri;


    public  DeleteParaleljaDto(int id, String emri) {
        this.id = id;
        this.emri = emri;

    }

    public int getId() {
        return id;
    }

    public String getEmri() {
        return emri;
    }

}
