package com.suivi.suivi_candidature.service;

import com.suivi.suivi_candidature.entity.Entreprise;
import java.util.List;

public interface EntrepriseService {
    List<Entreprise> findAll();
    Entreprise findById(String id);
    Entreprise create(Entreprise entreprise);
    Entreprise update(String id, Entreprise entreprise);
    void delete(String id);
}
