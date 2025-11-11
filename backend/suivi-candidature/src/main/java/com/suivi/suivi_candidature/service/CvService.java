package com.suivi.suivi_candidature.service;

import com.suivi.suivi_candidature.entity.Cv;
import java.util.List;

public interface CvService {
    List<Cv> findAll();
    Cv findById(String id);
    Cv create(Cv cv);
    Cv update(String id, Cv cv);
    void delete(String id);
}
