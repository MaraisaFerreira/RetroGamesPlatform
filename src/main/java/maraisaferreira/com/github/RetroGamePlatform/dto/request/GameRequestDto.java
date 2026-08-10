package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.*;
import maraisaferreira.com.github.RetroGamePlatform.messages.MessagesCenter;

import java.util.List;

public record GameRequestDto(
        @NotBlank(message = MessagesCenter.notNullOrEmpty)
        String name,

        @NotNull(message = MessagesCenter.notNullOrEmpty)
        @Min(value = 1970, message = MessagesCenter.minValue + "1970.")
        @Max(value = 2010, message = MessagesCenter.maxValue + "2010.")
        Integer releaseYear,

        String gameType,

        @NotNull(message = MessagesCenter.notNullOrEmpty)
        @Size(min = 1, message = MessagesCenter.atLestOneId)
        List<Long> consoleIds
) {
}
