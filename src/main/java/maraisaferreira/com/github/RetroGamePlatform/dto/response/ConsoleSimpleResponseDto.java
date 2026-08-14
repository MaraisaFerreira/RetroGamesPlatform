package maraisaferreira.com.github.RetroGamePlatform.dto.response;

import maraisaferreira.com.github.RetroGamePlatform.model.Console;

public record ConsoleSimpleResponseDto(
        Long id,
        String name
) {
    public ConsoleSimpleResponseDto(Console console) {
        this(
                console.getId(),
                console.getName()
        );
    }
}
