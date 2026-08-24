package maraisaferreira.com.github.RetroGamePlatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import maraisaferreira.com.github.RetroGamePlatform.constants.AppConstants;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ValidationMessages;
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
    @Min(value = AppConstants.LOWEST_GAME_YEAR, message = ValidationMessages.GAME_YEAR)
    @Max(value = AppConstants.HIGHEST_GAME_YEAR, message = ValidationMessages.GAME_YEAR)
    private Integer releaseYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameType gameType;

    @Column(unique = true)
    private String cover;

    @Getter(AccessLevel.NONE)
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

    public Set<Console> getConsoles(){
        return Set.copyOf(consoles);
    }

    public void addConsole(Console console) {
        if (console != null) consoles.add(console);
    }

    public void removeConsole(Console console) {
        if (console != null) consoles.remove(console);
    }

    public void clearRelation(Console console) {
        this.consoles.remove(console);
        console.removeGame(this);
    }
}
