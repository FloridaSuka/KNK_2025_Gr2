package models.dto.create;

public class CreateMesimiDTO {
    private int lendaId;
    private int profesoriId;
    private int klasaId;
    private int drejtimiId;

    public CreateMesimiDTO(int lendaId, int profesoriId, int klasaId, int drejtimiId) {
        this.lendaId = lendaId;
        this.profesoriId = profesoriId;
        this.klasaId = klasaId;
        this.drejtimiId = drejtimiId;
    }

    public int getLendaId() {
        return lendaId;
    }

    public int getProfesoriId() {
        return profesoriId;
    }

    public int getKlasaId() {
        return klasaId;
    }

    public int getDrejtimiId() {
        return drejtimiId;
    }
}

