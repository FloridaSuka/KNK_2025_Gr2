package models.dto.delete;

public class DeleteLenda {
    private int idLenda;

    public DeleteLenda(int idLenda) {
        this.idLenda = idLenda;
    }

    public int getIdLenda() { return idLenda; }

    public void setIdLenda(int idLenda) { this.idLenda = idLenda; }
}
