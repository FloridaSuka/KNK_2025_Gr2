package controllers;

import models.Adresa;
import services.AdresaService;

import java.util.List;

public class AdresaController {

    private AdresaService adresaService;

    public AdresaController() {
        adresaService = new AdresaService();
    }
    
    public void displayAdresat() {
        List<Adresa> adresat = adresaService.getAdresat();
        for (Adresa adresa : adresat) {
            System.out.println("ID: " + adresa.getId() + ", Qyteti: " + adresa.getQyteti() +
                    ", Rruga: " + adresa.getRruga() + ", Kodi Postar: " + adresa.getKodiPostar());
        }
    }

    public void addAdresa(Adresa adresa) {
        adresaService.addAdresa(adresa);
        System.out.println("Adresa është shtuar me sukses.");
    }

    public void updateAdresa(Adresa adresa) {
        adresaService.updateAdresa(adresa);
        System.out.println("Adresa është përditësuar me sukses.");
    }

    public void deleteAdresa(int id) {
        adresaService.deleteAdresa(id);
        System.out.println("Adresa është fshirë me sukses.");
    }
}

