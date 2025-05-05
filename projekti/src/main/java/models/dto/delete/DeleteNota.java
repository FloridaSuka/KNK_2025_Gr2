package models.dto.delete;

public class DeleteNota {

    private int id;

    public DeleteNota() {}
    public DeleteNota(int id) { this.id = id; }

    public int getId()            { return id; }
    public void setId(int id)     { this.id = id; }

    @Override
    public String toString() {
        return "DeleteNota{id=" + id + '}';
    }
}
