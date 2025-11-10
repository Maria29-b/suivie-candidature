package com.suivi.suivi_candidature.repository;

import com.suivi.suivi_candidature.entity.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, String> {
}