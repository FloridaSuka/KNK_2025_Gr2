package models.dto.update;

public class UpdateKlasa {
    private int id;
    private int niveli;
    private int shkollaId;
    private int paraleljaId;
    private int mesuesiId;
    private int drejtimiId;

    public UpdateKlasa(int id, int niveli, int shkollaId, int paraleljaId, int mesuesiId, int drejtimiId) {
        this.id = id;
        this.niveli = niveli;
        this.shkollaId = shkollaId;
        this.paraleljaId = paraleljaId;
        this.mesuesiId = mesuesiId;
        this.drejtimiId = drejtimiId;
    }

    public int getId() { return id; }
    public int getNiveli() { return niveli; }
    public int getShkollaId() { return shkollaId; }
    public int getParaleljaId() { return paraleljaId; }
    public int getMesuesiId() { return mesuesiId; }
    public int getDrejtimiId() { return drejtimiId; }
}
