package maraisaferreira.com.github.RetroGamePlatform.dto.response;

import maraisaferreira.com.github.RetroGamePlatform.model.Console;

public record ConsoleResponseDto(
        Long id,
        String name,
        String acronym,
        String origin
) {
    public ConsoleResponseDto(Console console) {
        this(
                console.getId(),
                console.getName(),
                console.getAcronym(),
                console.getOrigin()
        );
    }
}
