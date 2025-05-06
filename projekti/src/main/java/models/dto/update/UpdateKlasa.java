package models.dto.update;

public class UpdateKlasa {
    private int id;
    private int niveli;
    private int shkollaId;
    private int paraleljaId;
    private int profesoriId;
    private int drejtimiId;

    public UpdateKlasa(int id, int niveli, int shkollaId, int paraleljaId, int profesoriId, int drejtimiId) {
        this.id = id;
        this.niveli = niveli;
        this.shkollaId = shkollaId;
        this.paraleljaId = paraleljaId;
        this.profesoriId = profesoriId;
        this.drejtimiId = drejtimiId;
    }

    public int getId() {
        return id;
    }

    public int getNiveli() {
        return niveli;
    }

    public int getShkollaId() {
        return shkollaId;
    }

    public int getParaleljaId() {
        return paraleljaId;
    }

    public int getProfesoriId() {
        return profesoriId;
    }

    public int getDrejtimiId() {
        return drejtimiId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNiveli(int niveli) {
        this.niveli = niveli;
    }

    public void setShkollaId(int shkollaId) {
        this.shkollaId = shkollaId;
    }

    public void setParaleljaId(int paraleljaId) {
        this.paraleljaId = paraleljaId;
    }

    public void setProfesoriId(int profesoriId) {
        this.profesoriId = profesoriId;
    }

    public void setDrejtimiId(int drejtimiId) {
        this.drejtimiId = drejtimiId;
    }
}
