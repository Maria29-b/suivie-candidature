package com.suivi.suivi_candidature.security;

import com.suivi.suivi_candidature.entity.Candidat;
import com.suivi.suivi_candidature.repository.CandidatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidatDetailsService implements UserDetailsService {

    private final CandidatRepository candidatRepository;

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        return candidatRepository.findByPseudo(pseudo)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Candidat non trouvé: " + pseudo));
    }

    private UserDetails createUserDetails(Candidat candidat) {
        return User.builder()
                .username(candidat.getPseudo())
                .password(candidat.getPassword())
                .authorities("ROLE_USER") // Autorisation de base
                .build();
    }
}