package maraisaferreira.com.github.RetroGamePlatform.repositories;

import maraisaferreira.com.github.RetroGamePlatform.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
    Optional<Player> findByEmail(String email);
}
