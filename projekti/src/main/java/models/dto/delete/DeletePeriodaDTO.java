package models.dto.delete;

public class DeletePeriodaDTO {
    private int idPerioda;

    public DeletePeriodaDTO(int idPerioda) {
        this.idPerioda = idPerioda;
    }

    public int getIdPerioda() { return idPerioda; }

    public void setIdPerioda(int idPerioda) { this.idPerioda = idPerioda; }
}
