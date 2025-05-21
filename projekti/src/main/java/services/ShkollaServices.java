package services;

import models.dto.create.CreateMesuesi;
import models.dto.create.CreateShkolla;
import models.dto.update.UpdateMesuesi;
import models.dto.update.UpdateShkolla;
import repositories.BaseRepository;
import repositories.MesuesiRepository;
import repositories.ShkollaRepository;

public class ShkollaServices {
    private final ShkollaRepository repo = new ShkollaRepository();

    public boolean shto(CreateShkolla shkolla) {
        return repo.shtoShkollen(shkolla);
    }

    public boolean perditeso(UpdateShkolla shkolla) {
        return repo.perditesoShkollen(shkolla);
    }

    public boolean fshij(int id) {
        return repo.fshijShkollen(id);
    }
}
