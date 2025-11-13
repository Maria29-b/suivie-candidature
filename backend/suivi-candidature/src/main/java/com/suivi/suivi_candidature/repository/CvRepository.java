package com.suivi.suivi_candidature.repository;

import com.suivi.suivi_candidature.entity.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvRepository extends JpaRepository<Cv, String> {
}
