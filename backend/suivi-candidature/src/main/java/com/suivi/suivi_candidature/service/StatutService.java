package com.suivi.suivi_candidature.service;

import com.suivi.suivi_candidature.entity.Statut;
import java.util.List;

public interface StatutService {
    List<Statut> findAll();
    Statut findById(String id);
    Statut create(Statut statut);
    Statut update(String id, Statut statut);
    void delete(String id);
}
