package maraisaferreira.com.github.RetroGamePlatform.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "consoles")
public class Console {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(unique = true, length = 20)
    private String acronym;

    @Column(length = 100)
    private String origin;

    @Getter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "consoles")
    private final Set<Game> games = new HashSet<>();

    public Set<Game> getGames() {
        return Set.copyOf(games);
    }

    public void addGame(Game game) {
        if (game != null) games.add(game);
    }

    public void removeGame(Game game) {
        if (game != null) games.remove(game);
    }
}
