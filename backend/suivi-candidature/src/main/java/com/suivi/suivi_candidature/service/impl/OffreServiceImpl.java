package com.suivi.suivi_candidature.service.impl;

import com.suivi.suivi_candidature.entity.Offre;
import com.suivi.suivi_candidature.exception.ResourceNotFoundException;
import com.suivi.suivi_candidature.repository.OffreRepository;
import com.suivi.suivi_candidature.service.OffreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OffreServiceImpl implements OffreService {

    private final OffreRepository offreRepository;

    @Override
    public List<Offre> findAll() {
        return offreRepository.findAll();
    }

    @Override
    public Offre findById(String id) {
        return offreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offre introuvable: " + id));
    }

    @Override
    public Offre create(Offre offre) {
        return offreRepository.save(offre);
    }

    @Override
    public Offre update(String id, Offre offre) {
        Offre exist = findById(id);
        exist.setTitrePoste(offre.getTitrePoste());
        exist.setDescription(offre.getDescription());
        exist.setLienSource(offre.getLienSource());
        exist.setEntreprise(offre.getEntreprise()); // attention: client doit fournir entreprise.id existant
        return offreRepository.save(exist);
    }

    @Override
    public void delete(String id) {
        Offre exist = findById(id);
        offreRepository.delete(exist);
    }
}
