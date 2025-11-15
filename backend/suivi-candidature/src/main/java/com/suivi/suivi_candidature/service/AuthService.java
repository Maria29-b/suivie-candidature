package com.suivi.suivi_candidature.service;

import com.suivi.suivi_candidature.dto.AuthRequest;
import com.suivi.suivi_candidature.dto.AuthResponse;
import com.suivi.suivi_candidature.dto.RegisterRequest;
import com.suivi.suivi_candidature.entity.Candidat;
import com.suivi.suivi_candidature.repository.CandidatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CandidatRepository candidatRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Crée un nouvel utilisateur et renvoie un token JWT.
     */
    public AuthResponse register(RegisterRequest request) {
        // Vérifier si le pseudo existe déjà
        if (candidatRepository.existsByPseudo(request.getPseudo())) {
            throw new RuntimeException("Pseudo déjà utilisé");
        }

        // Créer le candidat avec mot de passe haché
        Candidat candidat = Candidat.builder()
                .nomUser(request.getNomUser())
                .pseudo(request.getPseudo())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        candidatRepository.save(candidat);

        // Générer le token JWT
        String jwtToken = jwtService.generateToken(candidat.getPseudo());

        return AuthResponse.builder()
                .token(jwtToken)
                .pseudo(candidat.getPseudo())
                .build();
    }

    /**
     * Authentifie un utilisateur existant et renvoie un token JWT.
     */
    public AuthResponse authenticate(AuthRequest request) {
        // Récupérer l'utilisateur par pseudo
        Candidat candidat = candidatRepository.findByPseudo(request.getPseudo())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Vérifier le mot de passe
        if (!passwordEncoder.matches(request.getPassword(), candidat.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        // Générer le token JWT
        String jwtToken = jwtService.generateToken(candidat.getPseudo());

        return AuthResponse.builder()
                .token(jwtToken)
                .pseudo(candidat.getPseudo())
                .build();
    }
}
