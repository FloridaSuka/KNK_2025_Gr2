package models;

import models.Adresa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Administratori {
    private int ID;
    private User user;
    private String emailZyrtar;
    private String telefoni;
    private String departamenti;
    private boolean aktiv;
    private LocalDateTime dataKrijimit;

    private Administratori(int ID, User user, String emailZyrtar, String telefoni, String departamenti, boolean aktiv, LocalDateTime dataKrijimit) {
        this.ID = ID;
        this.user = user;
        this.emailZyrtar = emailZyrtar;
        this.telefoni = telefoni;
        this.departamenti = departamenti;
        this.aktiv = aktiv;
        this.dataKrijimit = dataKrijimit;
    }

    public static Administratori getInstance(ResultSet result) throws SQLException {
        int ID = result.getInt("ID");
        User user = User.getInstance(result);
        String emailZyrtar = result.getString("emailZyrtar");
        String telefoni = result.getString("telefoni");
        String departamenti = result.getString("departamenti");
        boolean aktiv = result.getBoolean("aktiv");
        LocalDateTime dataKrijimit = result.getObject("dataKrijimit", LocalDateTime.class);
        return new Administratori(ID, user, emailZyrtar, telefoni, departamenti, aktiv, dataKrijimit);
    }

    public int getID() {
        return ID;
    }

    public User getUser() {
        return user;
    }

    public String getEmailZyrtar() {
        return emailZyrtar;
    }

    public String getTelefoni() {
        return telefoni;
    }

    public String getDepartamenti() {
        return departamenti;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public LocalDateTime getDataKrijimit() {
        return dataKrijimit;
    }
}
