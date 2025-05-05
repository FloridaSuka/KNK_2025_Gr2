package main.java.models.dto.create;

public class CreateParaleljaDto {
    private int id;
    private String emri;


    public  CreateParaleljaDto(int id, String emri) {
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
