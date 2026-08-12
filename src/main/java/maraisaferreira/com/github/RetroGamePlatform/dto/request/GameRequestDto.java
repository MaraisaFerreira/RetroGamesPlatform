package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.*;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ValidationMessages;

import java.util.List;

public record GameRequestDto(
        @NotBlank(message = ValidationMessages.notNullOrEmpty)
        String name,

        @NotNull(message = ValidationMessages.notNullOrEmpty)
        @Min(value = 1970, message = ValidationMessages.minValue + "1970.")
        @Max(value = 2010, message = ValidationMessages.maxValue + "2010.")
        Integer releaseYear,

        String gameType,

        @NotNull(message = ValidationMessages.notNullOrEmpty)
        @Size(min = 1, message = ValidationMessages.atLestOneId)
        List<Long> consoleIds
) {
}
