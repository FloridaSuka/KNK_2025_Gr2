package models.dto.delete;

public class DeleteUser{

    private int id;

    public DeleteUser() {}
    public DeleteUser(int id) { this.id = id; }

    public int getId()         { return id; }
    public void setId(int id)  { this.id = id; }

    @Override
    public String toString() {
        return "DeleteUser{id=" + id + '}';
    }
}
