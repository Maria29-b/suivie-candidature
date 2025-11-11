package com.suivi.suivi_candidature.repository;

import com.suivi.suivi_candidature.entity.Motivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivationRepository extends JpaRepository<Motivation, String> {
}
