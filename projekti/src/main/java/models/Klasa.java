package models;

import models.Adresa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Klasa {
    private int id;
    private int niveli;
    private int shkollaId;
    private int paraleljaId;
    private int profesoriId;
    private int drejtimiId;


    private Klasa(int id, int niveli, int shkollaId, int paraleljaId, int profesoriId, int drejtimiId) {
        this.id = id;
        this.niveli = niveli;
        this.shkollaId = shkollaId;
        this.paraleljaId = paraleljaId;
        this.profesoriId = profesoriId;
        this.drejtimiId = drejtimiId;
    }

    public static Klasa getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        int niveli = result.getInt("niveli");
        int shkollaId = result.getInt("shkolla_id");
        int paraleljaId = result.getInt("paralelja_id");
        int profesoriId = result.getInt("profesori_id");
        int drejtimiId = result.getInt("drejtimi_id");

        return new Klasa(id, niveli, shkollaId, paraleljaId, profesoriId, drejtimiId);
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
}