package com.suivi.suivi_candidature.service.impl;

import com.suivi.suivi_candidature.entity.Postuler;
import com.suivi.suivi_candidature.entity.PostulerId;
import com.suivi.suivi_candidature.exception.ResourceNotFoundException;
import com.suivi.suivi_candidature.repository.PostulerRepository;
import com.suivi.suivi_candidature.service.PostulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostulerServiceImpl implements PostulerService {

    private final PostulerRepository postulerRepository;

    @Override
    public List<Postuler> findAll() {
        return postulerRepository.findAll();
    }

    @Override
    public Postuler findById(PostulerId id) {
        return postulerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postuler introuvable: " + id));
    }

    @Override
    public Postuler create(Postuler postuler) {
        return postulerRepository.save(postuler);
    }

    @Override
    public Postuler update(PostulerId id, Postuler postuler) {
        Postuler exist = findById(id);
        exist.setDateCandidature(postuler.getDateCandidature());
        // si besoin, mettre à jour références entreprise/candidat/statut via insertable=false champs
        return postulerRepository.save(exist);
    }

    @Override
    public void delete(PostulerId id) {
        Postuler exist = findById(id);
        postulerRepository.delete(exist);
    }
}
