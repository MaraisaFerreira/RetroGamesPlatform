package maraisaferreira.com.github.RetroGamePlatform.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import maraisaferreira.com.github.RetroGamePlatform.model.Player;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.token.secret}")
    private String secret;

    @Bean
    public String generateToken(Player player) {
        return JWT.create()
                .withIssuer("Retro Game Platform")
                .withSubject(player.getEmail())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusMillis(1800000))
                .sign(Algorithm.HMAC256(secret));
    }
}
