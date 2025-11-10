package com.suivi.suivi_candidature.service.impl;

import com.suivi.suivi_candidature.entity.Entreprise;
import com.suivi.suivi_candidature.exception.ResourceNotFoundException;
import com.suivi.suivi_candidature.repository.EntrepriseRepository;
import com.suivi.suivi_candidature.service.EntrepriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrepriseServiceImpl implements EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    @Override
    public List<Entreprise> findAll() {
        return entrepriseRepository.findAll();
    }

    @Override
    public Entreprise findById(String id) {
        return entrepriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise introuvable: " + id));
    }

    @Override
    public Entreprise create(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public Entreprise update(String id, Entreprise entreprise) {
        Entreprise exist = findById(id);
        // mettre à jour champs utiles (exemple)
        exist.setNomEnt(entreprise.getNomEnt());
        exist.setSiteWeb(entreprise.getSiteWeb());
        exist.setAdresse(entreprise.getAdresse());
        exist.setContact(entreprise.getContact());
        return entrepriseRepository.save(exist);
    }

    @Override
    public void delete(String id) {
        Entreprise exist = findById(id);
        entrepriseRepository.delete(exist);
    }
}
