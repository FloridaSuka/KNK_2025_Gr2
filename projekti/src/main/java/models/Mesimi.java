package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mesimi {
    private int lendaId;
    private int profesoriId;
    private int klasaId;
    private int drejtimiId;

   public Mesimi(int lendaId, int profesoriId, int klasaId, int drejtimiId) {
        this.lendaId = lendaId;
        this.profesoriId = profesoriId;
        this.klasaId = klasaId;
        this.drejtimiId = drejtimiId;
    }

    public static Mesimi getInstance(ResultSet result) throws SQLException {
        int lendaId = result.getInt("lid");
        int profesoriId = result.getInt("pid");
        int klasaId = result.getInt("klid");
        int drejtimiId = result.getInt("did");

        return new Mesimi(lendaId, profesoriId, klasaId, drejtimiId);
    }

    public int getLendaId() { return lendaId; }
    public int getProfesoriId() { return profesoriId; }
    public int getKlasaId() { return klasaId; }
    public int getDrejtimiId() { return drejtimiId; }
}


