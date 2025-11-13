package com.suivi.suivi_candidature.service;

import com.suivi.suivi_candidature.entity.Offre;
import com.suivi.suivi_candidature.repository.OffreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffreService {

    private final OffreRepository offreRepository;

    public OffreService(OffreRepository offreRepository) {
        this.offreRepository = offreRepository;
    }

    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    public Optional<Offre> getOffreById(String id) {
        return offreRepository.findById(id);
    }

    public Offre createOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    public Offre updateOffre(String id, Offre offreDetails) {
        return offreRepository.findById(id)
                .map(offre -> {
                    offre.setTitrePoste(offreDetails.getTitrePoste());
                    offre.setDescription(offreDetails.getDescription());
                    offre.setLienSource(offreDetails.getLienSource());
                    offre.setEntreprise(offreDetails.getEntreprise());
                    return offreRepository.save(offre);
                })
                .orElseThrow(() -> new RuntimeException("Offre non trouvée"));
    }

    public void deleteOffre(String id) {
        offreRepository.deleteById(id);
    }
}
