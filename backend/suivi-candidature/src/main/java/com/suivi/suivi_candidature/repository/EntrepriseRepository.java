package com.suivi.suivi_candidature.repository;

import com.suivi.suivi_candidature.entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, String> {
}
