package com.suivi.suivi_candidature.repository;

import com.suivi.suivi_candidature.entity.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffreRepository extends JpaRepository<Offre, String> {
}
