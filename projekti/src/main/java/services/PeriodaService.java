package services;

import models.Perioda;
import java.util.ArrayList;
import java.util.List;

public class PeriodaService {
    private final List<Perioda> periodat = new ArrayList<>();

    public void shtoPerioda(Perioda perioda) {
        periodat.add(perioda);
    }

    public List<Perioda> merrTeGjitha() {
        return periodat;
    }

    public Perioda gjejSipasId(int id) {
        for (Perioda p : periodat) {
            if (p.getIdPerioda() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean fshijPerioda(int id) {
        return periodat.removeIf(p -> p.getIdPerioda() == id);
    }
}
