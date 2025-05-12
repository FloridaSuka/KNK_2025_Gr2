package models;

public class Notat {
    private int id;
    private int nxenesiId;
    private int lendaId;
    private int punonjesiId;
    private int drejtimiId;
    private int klasaId;
    private int paraleljaId;
    private int notaPare;
    private int notaDyte;

    public Notat(int id, int nxenesiId, int lendaId, int punonjesiId, int drejtimiId, int klasaId, int paraleljaId, int notaPare, int notaDyte) {
        this.id = id;
        this.nxenesiId = nxenesiId;
        this.lendaId = lendaId;
        this.punonjesiId = punonjesiId;
        this.drejtimiId = drejtimiId;
        this.klasaId = klasaId;
        this.paraleljaId = paraleljaId;
        this.notaPare = notaPare;
        this.notaDyte = notaDyte;
    }

    // Getters
    public int getId()          { return id; }
    public int getNxenesiId()   { return nxenesiId; }
    public int getLendaId()     { return lendaId; }
    public int getPunonjesiId() { return punonjesiId; }
    public int getDrejtimiId()  { return drejtimiId; }
    public int getKlasaId()     { return klasaId; }
    public int getParaleljaId() { return paraleljaId; }
    public int getNotaPare()    { return notaPare; }
    public int getNotaDyte()    { return notaDyte; }
}
