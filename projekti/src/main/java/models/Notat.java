package models;

public class Notat {
    public static final int MIN_NOTA = 1;
    public static final int MAX_NOTA = 5;

    private int id;
    private int nxenesiId;
    private int lendaId;
    private int punonjesiId;
    private int drejtimiId;
    private int klasaId;
    private int paraleljaId;
    private int notaPare;
    private int notaDyte;
    private int notaFinale;

    public Notat(int id, int nxenesiId, int lendaId, int punonjesiId, int drejtimiId, int klasaId, int paraleljaId, int notaPare, int notaDyte) {
        this.id = id;
        this.nxenesiId = nxenesiId;
        this.lendaId = lendaId;
        this.punonjesiId = punonjesiId;
        this.drejtimiId = drejtimiId;
        this.klasaId = klasaId;
        this.paraleljaId = paraleljaId;
        setNotaPare(notaPare);
        setNotaDyte(notaDyte);
    }

    public Notat(int id, int notaPare, int notaDyte) {
        this(id, 0, 0, 0, 0, 0, 0, notaPare, notaDyte);
    }

    // ======= GETTERS =======
    public int getId()          { return id; }
    public int getNxenesiId()   { return nxenesiId; }
    public int getLendaId()     { return lendaId; }
    public int getPunonjesiId() { return punonjesiId; }
    public int getDrejtimiId()  { return drejtimiId; }
    public int getKlasaId()     { return klasaId; }
    public int getParaleljaId() { return paraleljaId; }
    public int getNotaPare()    { return notaPare; }
    public int getNotaDyte()    { return notaDyte; }
    public int getNotaFinale()  { return notaFinale; }

    // ======= SETTERS =======
    public void setNxenesiId(int nxenesiId)   { this.nxenesiId = nxenesiId; }
    public void setLendaId(int lendaId)       { this.lendaId = lendaId; }
    public void setPunonjesiId(int punonjesiId){ this.punonjesiId = punonjesiId; }
    public void setDrejtimiId(int drejtimiId) { this.drejtimiId = drejtimiId; }
    public void setKlasaId(int klasaId)       { this.klasaId = klasaId; }
    public void setParaleljaId(int paraleljaId){ this.paraleljaId = paraleljaId; }

    public void setNotaPare(int notaPare) {
        this.notaPare = validoNota(notaPare);
        kalkulimiNotaFinale();
    }

    public void setNotaDyte(int notaDyte) {
        this.notaDyte = validoNota(notaDyte);
        kalkulimiNotaFinale();
    }

    private int validoNota(int nota) {
        if (nota < MIN_NOTA || nota > MAX_NOTA) {
            throw new IllegalArgumentException(
                    "Nota duhet të jetë mes " + MIN_NOTA + " dhe " + MAX_NOTA + ". U dha: " + nota);
        }
        return nota;
    }

    private void kalkulimiNotaFinale() {
        this.notaFinale = (notaPare + notaDyte) / 2;  // rezultat integer 1–5
    }

    public double mesatarjaPrecise() {
        return (notaPare + notaDyte) / 2.0;
    }

    @Override
    public String toString() {
        return String.format(
                "Notat{id=%d, nxenesiId=%d, lendaId=%d, notaPare=%d, notaDyte=%d, notaFinale=%d}",
                id, nxenesiId, lendaId, notaPare, notaDyte, notaFinale
        );
    }
}
