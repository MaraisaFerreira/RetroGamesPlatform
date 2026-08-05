package maraisaferreira.com.github.RetroGamePlatform.repositories;

import maraisaferreira.com.github.RetroGamePlatform.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
