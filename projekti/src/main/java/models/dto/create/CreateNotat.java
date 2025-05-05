package models.dto.create;

public class CreateNotat {

    private Integer nxenesiId;
    private Integer lendaId;
    private Integer punonjesiId;
    private Integer drejtimiId;
    private Integer klasaId;
    private Integer paraleljaId;
    private Integer notaPare;
    private Integer notaDyte;

    /* ===== GETTERS / SETTERS ===== */
    public Integer getNxenesiId()  { return nxenesiId; }
    public void setNxenesiId(Integer nxenesiId) { this.nxenesiId = nxenesiId; }

    public Integer getLendaId()    { return lendaId; }
    public void setLendaId(Integer lendaId) { this.lendaId = lendaId; }

    public Integer getPunonjesiId(){ return punonjesiId; }
    public void setPunonjesiId(Integer punonjesiId) { this.punonjesiId = punonjesiId; }

    public Integer getDrejtimiId() { return drejtimiId; }
    public void setDrejtimiId(Integer drejtimiId) { this.drejtimiId = drejtimiId; }

    public Integer getKlasaId()    { return klasaId; }
    public void setKlasaId(Integer klasaId) { this.klasaId = klasaId; }

    public Integer getParaleljaId(){ return paraleljaId; }
    public void setParaleljaId(Integer paraleljaId) { this.paraleljaId = paraleljaId; }

    public Integer getNotaPare()   { return notaPare; }
    public void setNotaPare(Integer notaPare) { this.notaPare = notaPare; }

    public Integer getNotaDyte()   { return notaDyte; }
    public void setNotaDyte(Integer notaDyte) { this.notaDyte = notaDyte; }
}
