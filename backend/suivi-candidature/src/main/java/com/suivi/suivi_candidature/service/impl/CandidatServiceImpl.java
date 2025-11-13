package com.suivi.suivi_candidature.service.impl;

import com.suivi.suivi_candidature.entity.Candidat;
import com.suivi.suivi_candidature.exception.ResourceNotFoundException;
import com.suivi.suivi_candidature.repository.CandidatRepository;
import com.suivi.suivi_candidature.service.CandidatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidatServiceImpl implements CandidatService {

    private final CandidatRepository candidatRepository;

    @Override
    public List<Candidat> findAll() {
        return candidatRepository.findAll();
    }

    @Override
    public Candidat findById(String id) {
        return candidatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidat introuvable: " + id));
    }

    @Override
    public Candidat create(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    @Override
    public Candidat update(String id, Candidat candidat) {
        Candidat exist = findById(id);
        exist.setNomUser(candidat.getNomUser());
        return candidatRepository.save(exist);
    }

    @Override
    public void delete(String id) {
        Candidat exist = findById(id);
        candidatRepository.delete(exist);
    }
}
