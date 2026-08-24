package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import maraisaferreira.com.github.RetroGamePlatform.constants.AppConstants;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ValidationMessages;
import org.apache.logging.log4j.util.Strings;

public record GameUpdateRequestDto(
        String name,

        @Min(value = AppConstants.LOWEST_GAME_YEAR, message = ValidationMessages.GAME_YEAR)
        @Max(value = AppConstants.HIGHEST_GAME_YEAR, message = ValidationMessages.GAME_YEAR)
        Integer releaseYear,

        String gameType
) {
    @AssertTrue(message = ValidationMessages.NO_DATA_TO_UPDATE)
    public boolean hasAnyField() {
        return Strings.isNotBlank(name)
                || Strings.isNotBlank(gameType)
                || releaseYear != null;
    }
}
