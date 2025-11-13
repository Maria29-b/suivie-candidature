package com.suivi.suivi_candidature.repository;

import com.suivi.suivi_candidature.entity.Postuler;
import com.suivi.suivi_candidature.entity.PostulerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulerRepository extends JpaRepository<Postuler, PostulerId> {
}
