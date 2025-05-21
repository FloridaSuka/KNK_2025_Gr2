package services;

import models.Drejtor;
import repositories.DrejtoriRepository;

import java.util.List;

public class DrejtoriService {
    private final DrejtoriRepository repo = new DrejtoriRepository();

    public boolean shto(Drejtor d) {
        return repo.shtoDrejtor(d);
    }

    public boolean fshij(int id) {
        return repo.fshijDrejtor(id);
    }

    public boolean perditeso(Drejtor d) {
        return repo.perditesoDrejtor(d);
    }

    public List<Drejtor> gjejTeGjithe() {
        return repo.gjejTeGjithe();
    }
}
