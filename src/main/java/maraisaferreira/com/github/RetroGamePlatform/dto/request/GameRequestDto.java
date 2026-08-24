package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.*;
import maraisaferreira.com.github.RetroGamePlatform.constants.AppConstants;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ValidationMessages;

import java.util.List;

public record GameRequestDto(
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String name,

        @NotNull(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        @Min(value = AppConstants.LOWEST_GAME_YEAR, message = ValidationMessages.GAME_YEAR)
        @Max(value = AppConstants.HIGHEST_GAME_YEAR, message = ValidationMessages.GAME_YEAR)
        Integer releaseYear,

        String gameType,

        @NotNull(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        @Size(min = 1, message = ValidationMessages.SEND_AT_LEAST_ONE_ID)
        List<Long> consoleIds
) {
}
