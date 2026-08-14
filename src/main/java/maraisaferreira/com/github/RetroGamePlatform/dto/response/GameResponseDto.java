package maraisaferreira.com.github.RetroGamePlatform.dto.response;

import maraisaferreira.com.github.RetroGamePlatform.model.Game;
import maraisaferreira.com.github.RetroGamePlatform.model.enums.GameType;

import java.util.List;

public record GameResponseDto(
        Long id,
        String name,
        Integer releaseYear,
        GameType gameType,
        String cover,
        List<ConsoleSimpleResponseDto> consoles
) {
    public GameResponseDto(Game game) {
        this(
                game.getId(),
                game.getName(),
                game.getReleaseYear(),
                game.getGameType(),
                game.getCover(),
                game.getConsoles().stream().map(ConsoleSimpleResponseDto::new).toList()
        );
    }
}
