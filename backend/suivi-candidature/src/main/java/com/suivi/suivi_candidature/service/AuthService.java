package com.suivi.suivi_candidature.service;

import com.suivi.suivi_candidature.dto.AuthRequest;
import com.suivi.suivi_candidature.dto.AuthResponse;
import com.suivi.suivi_candidature.dto.RegisterRequest;
import com.suivi.suivi_candidature.entity.Candidat;
import com.suivi.suivi_candidature.repository.CandidatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CandidatRepository candidatRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        // Vérifier si le pseudo existe déjà
        if (candidatRepository.existsByPseudo(request.getPseudo())) {
            throw new RuntimeException("Pseudo déjà utilisé");
        }

        var candidat = Candidat.builder()
                .pseudo(request.getPseudo())
                .password(passwordEncoder.encode(request.getPassword()))
                .nomUser(request.getNomUser())
                .build();

        candidatRepository.save(candidat);

        var jwtToken = jwtService.generateToken(candidat.getPseudo());

        return AuthResponse.builder()
                .token(jwtToken)
                .pseudo(candidat.getPseudo())
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPseudo(),
                        request.getPassword()
                )
        );

        var candidat = candidatRepository.findByPseudo(request.getPseudo())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(candidat.getPseudo());

        return AuthResponse.builder()
                .token(jwtToken)
                .pseudo(candidat.getPseudo())
                .build();
    }
}