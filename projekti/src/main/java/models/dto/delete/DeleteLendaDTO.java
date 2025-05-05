package models.dto.delete;

public class DeleteLendaDTO {
    private int idLenda;

    public DeleteLendaDTO(int idLenda) {
        this.idLenda = idLenda;
    }

    public int getIdLenda() { return idLenda; }

    public void setIdLenda(int idLenda) { this.idLenda = idLenda; }
}
