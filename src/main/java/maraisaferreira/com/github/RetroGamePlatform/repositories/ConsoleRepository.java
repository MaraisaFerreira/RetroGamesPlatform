package maraisaferreira.com.github.RetroGamePlatform.repositories;

import maraisaferreira.com.github.RetroGamePlatform.model.Console;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConsoleRepository extends JpaRepository<Console, Long> {
    Optional<Console> findByName(String name);
    Optional<Console> findByAcronym(String acronym);

    @Query("""
        SELECT c
        FROM Console c
        WHERE LOWER(c.name) LIKE CONCAT('%', LOWER(:part), '%')
    """)
    Page<Console> findByPartName(@Param("part") String part, Pageable pageable);
}
