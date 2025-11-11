package com.suivi.suivi_candidature.repository;

import com.suivi.suivi_candidature.entity.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutRepository extends JpaRepository<Statut, String> {
}
