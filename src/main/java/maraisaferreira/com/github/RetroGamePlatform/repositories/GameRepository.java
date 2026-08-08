package maraisaferreira.com.github.RetroGamePlatform.repositories;

import maraisaferreira.com.github.RetroGamePlatform.model.Game;
import maraisaferreira.com.github.RetroGamePlatform.model.enums.GameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByName(String name);

    @Query("""
        SELECT g
        FROM Game g
        WHERE LOWER(g.name) LIKE CONCAT('%', LOWER(:part), '%')
    """)
    List<Game> findByPartName(@Param("part") String part);

    List<Game> findByGameType(GameType gameType);
}
