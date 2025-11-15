package com.suivi.suivi_candidature.service.impl;

import com.suivi.suivi_candidature.entity.Postuler;
import com.suivi.suivi_candidature.entity.PostulerId;
import com.suivi.suivi_candidature.entity.Candidat;
import com.suivi.suivi_candidature.exception.ResourceNotFoundException;
import com.suivi.suivi_candidature.repository.PostulerRepository;
import com.suivi.suivi_candidature.repository.CandidatRepository;
import com.suivi.suivi_candidature.service.PostulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostulerServiceImpl implements PostulerService {

    private final PostulerRepository postulerRepository;
    private final CandidatRepository candidatRepository;

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
    @Transactional
    public Postuler create(Postuler postuler) {
        log.info("Création d'une postulation avec idUser: {}", postuler.getIdUser());

        // Vérifier si le candidat existe, sinon le créer
        if (postuler.getIdUser() != null) {
            boolean candidatExists = candidatRepository.existsById(postuler.getIdUser());
            log.info("Candidat existe pour idUser {}: {}", postuler.getIdUser(), candidatExists);

            if (!candidatExists) {
                log.info("Création automatique du candidat manquant avec idUser: {}", postuler.getIdUser());
                // Créer un candidat par défaut avec l'ID fourni
                Candidat newCandidat = new Candidat();
                newCandidat.setIdUser(postuler.getIdUser());
                newCandidat.setNomUser("Utilisateur par défaut");
                newCandidat.setPseudo("user_" + UUID.randomUUID().toString().substring(0, 8));
                newCandidat.setPassword("$2a$10$dummyPasswordHash"); // Mot de passe hashé par défaut
                candidatRepository.save(newCandidat);
                log.info("Candidat créé avec succès");
            }
        }

        log.info("Sauvegarde de la postulation...");
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
