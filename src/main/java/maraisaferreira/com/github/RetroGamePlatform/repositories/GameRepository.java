package maraisaferreira.com.github.RetroGamePlatform.repositories;

import maraisaferreira.com.github.RetroGamePlatform.model.Game;
import maraisaferreira.com.github.RetroGamePlatform.model.enums.GameType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByName(String name);
    Optional<Game> findByCover(String cover);

    @Query("""
        SELECT g
        FROM Game g
        WHERE LOWER(g.name) LIKE CONCAT('%', LOWER(:part), '%')
    """)
    Page<Game> findByPartName(@Param("part") String part, Pageable pageable);

    Page<Game> findByGameType(GameType gameType, Pageable pageable);

    Page<Game> findByConsolesId(Long id, Pageable pageable);
}
