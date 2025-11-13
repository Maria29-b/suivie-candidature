package com.suivi.suivi_candidature.service;

import com.suivi.suivi_candidature.entity.Offre;
import java.util.List;

public interface OffreService {
    List<Offre> findAll();
    Offre findById(String id);
    Offre create(Offre offre);
    Offre update(String id, Offre offre);
    void delete(String id);
}
