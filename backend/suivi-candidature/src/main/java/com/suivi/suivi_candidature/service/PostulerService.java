package com.suivi.suivi_candidature.service;

import com.suivi.suivi_candidature.entity.Postuler;
import com.suivi.suivi_candidature.entity.PostulerId;
import java.util.List;

public interface PostulerService {
    List<Postuler> findAll();
    Postuler findById(PostulerId id);
    Postuler create(Postuler postuler);
    Postuler update(PostulerId id, Postuler postuler);
    void delete(PostulerId id);
}
