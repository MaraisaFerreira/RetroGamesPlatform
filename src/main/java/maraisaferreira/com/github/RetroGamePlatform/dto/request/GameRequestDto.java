package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.*;
import maraisaferreira.com.github.RetroGamePlatform.helpers.Messages;

import java.util.List;

public record GameRequestDto(
        @NotBlank(message = Messages.notNullOrEmpty)
        String name,

        @NotNull(message = Messages.notNullOrEmpty)
        @Min(value = 1970, message = Messages.minValue + "1970.")
        @Max(value = 2010, message = Messages.maxValue + "2010.")
        Integer releaseYear,

        String gameType,

        @NotNull(message = Messages.notNullOrEmpty)
        @Size(min = 1, message = Messages.atLestOneId)
        List<Long> consoleIds
) {
}
