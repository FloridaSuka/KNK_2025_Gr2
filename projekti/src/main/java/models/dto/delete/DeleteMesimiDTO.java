package models.dto.delete;

public class DeleteMesimiDTO {
    private int lendaId;

    public DeleteMesimiDTO(int lendaId) {
        this.lendaId = lendaId;
    }

    public int getLendaId() {
        return lendaId;
    }
}

