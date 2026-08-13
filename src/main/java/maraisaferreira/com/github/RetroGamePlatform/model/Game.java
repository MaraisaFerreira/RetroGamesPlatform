package maraisaferreira.com.github.RetroGamePlatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import maraisaferreira.com.github.RetroGamePlatform.model.enums.GameType;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Min(value = 1970, message = "The lower value allowed is 1970.")
    @Max(value = 2010, message = "The maximum value allowed is 2010.")
    private Integer releaseYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameType gameType;

    @Column(unique = true)
    private String cover;

    @ManyToMany
    @JoinTable(
            name = "game_console",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "console_id")
    )
    private final Set<Console> consoles = new HashSet<>();

    public Game(String name, Integer releaseYear, GameType gameType) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.gameType = gameType;
    }

    public void clearRelation(Console console) {
        this.consoles.remove(console);
        console.getGames().remove(this);

    }
}
