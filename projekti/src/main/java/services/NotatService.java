package services;

import models.Notat;

import java.util.ArrayList;
import java.util.List;

public class NotatService {

    public static final int MIN_NOTA = 1;
    public static final int MAX_NOTA = 5;

    private final List<Notat> notatList;

    public NotatService() {
        this.notatList = new ArrayList<>();
    }

    // Shto një notë të re
    public void shtoNota(Notat nota) {
        if (validoNota(nota.getNotaPare()) && validoNota(nota.getNotaDyte())) {
            notatList.add(nota);
        } else {
            System.out.println("Nota nuk është valide!");
        }
    }

    // Përditëso një notë ekzistuese
    public void perditesoNota(int id, int notaPare, int notaDyte) {
        for (Notat nota : notatList) {
            if (nota.getId() == id) {
                if (validoNota(notaPare) && validoNota(notaDyte)) {
                    notaPare = notaPare;
                    notaDyte = notaDyte;
                    System.out.println("Nota u përditësua me sukses.");
                } else {
                    System.out.println("Nota nuk është valide!");
                }
                return;
            }
        }
        System.out.println("Nota me ID " + id + " nuk u gjet.");
    }

    // Fshij një notë sipas ID-së
    public void fshijNota(int id) {
        notatList.removeIf(nota -> nota.getId() == id);
    }

    // Shfaq të gjitha notat
    public List<Notat> getTeGjithaNotat() {
        return notatList;
    }

    // Kalkulimi i notës finale
    public double kalkuloNotaFinale(Notat nota) {
        return (nota.getNotaPare() + nota.getNotaDyte()) / 2.0;
    }

    // Validimi i notës
    private boolean validoNota(int nota) {
        return nota >= MIN_NOTA && nota <= MAX_NOTA;
    }
}
