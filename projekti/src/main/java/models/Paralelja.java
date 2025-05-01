package main.java.models;

import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Paralelja {
    private int id;
    private String emri;


    private Paralelja(int id,String emri) {
        this.id = id;
        this.emri = emri;


    }

    public static Paralelja getInstance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String emri = result.getString("emri");


        return new Paralelja(id,emri);
    }

    public int getId() {
        return id;
    }

    public String getEmri() {
        return emri;
    }


}