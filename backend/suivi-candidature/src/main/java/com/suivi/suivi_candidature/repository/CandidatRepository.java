package com.suivi.suivi_candidature.repository;

import com.suivi.suivi_candidature.entity.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, String> {

    Optional<Candidat> findByPseudo(String pseudo);

    boolean existsByPseudo(String pseudo);
}