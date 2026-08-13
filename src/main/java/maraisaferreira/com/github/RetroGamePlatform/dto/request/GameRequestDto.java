package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.*;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ValidationMessages;

import java.util.List;

public record GameRequestDto(
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String name,

        @NotNull(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        @Min(value = 1970, message = ValidationMessages.LOWER_VALUE_ALLOWED + "1970.")
        @Max(value = 2010, message = ValidationMessages.MAX_VALUE_ALLOWED + "2010.")
        Integer releaseYear,

        String gameType,

        @NotNull(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        @Size(min = 1, message = ValidationMessages.SEND_AT_LEAST_ONE_ID)
        List<Long> consoleIds
) {
}
