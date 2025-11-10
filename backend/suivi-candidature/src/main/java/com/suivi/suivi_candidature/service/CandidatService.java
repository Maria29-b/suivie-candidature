package com.suivi.suivi_candidature.service;

import com.suivi.suivi_candidature.entity.Candidat;
import java.util.List;

public interface CandidatService {
    List<Candidat> findAll();
    Candidat findById(String id);
    Candidat create(Candidat candidat);
    Candidat update(String id, Candidat candidat);
    void delete(String id);
}
