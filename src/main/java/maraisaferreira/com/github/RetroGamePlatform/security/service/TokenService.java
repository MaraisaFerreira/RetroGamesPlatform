package maraisaferreira.com.github.RetroGamePlatform.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import maraisaferreira.com.github.RetroGamePlatform.model.Player;
import maraisaferreira.com.github.RetroGamePlatform.model.enums.Roles;
import maraisaferreira.com.github.RetroGamePlatform.security.dto.response.TokenDataDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.token.secret}")
    private String secret;
    private static final String issuer = "Retro Game Platform";

    public String generateToken(Player player) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(player.getEmail())
                .withClaim("role", player.getSysRole().toString())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusMillis(900000))
                .sign(Algorithm.HMAC256(secret));
    }

    public TokenDataDto validateToken(String token) {
        DecodedJWT decoded = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(issuer)
                .build()
                .verify(token);

        return new TokenDataDto(
                decoded.getSubject(),
                Roles.valueOf(decoded.getClaim("role").asString())
        );
    }
}
