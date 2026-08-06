package maraisaferreira.com.github.RetroGamePlatform.repositories;

import maraisaferreira.com.github.RetroGamePlatform.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsoleRepository extends JpaRepository<Console, Long> {
    Optional<Console> findByAcronym(String acronym);
}
