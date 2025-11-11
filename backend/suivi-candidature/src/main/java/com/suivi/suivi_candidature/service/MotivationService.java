package com.suivi.suivi_candidature.service;

import com.suivi.suivi_candidature.entity.Motivation;
import java.util.List;

public interface MotivationService {
    List<Motivation> findAll();
    Motivation findById(String id);
    Motivation create(Motivation motivation);
    Motivation update(String id, Motivation motivation);
    void delete(String id);
}
