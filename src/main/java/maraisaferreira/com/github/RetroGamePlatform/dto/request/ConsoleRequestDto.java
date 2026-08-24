package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.NotBlank;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ValidationMessages;
import org.hibernate.validator.constraints.Length;

public record ConsoleRequestDto(
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String name,

        @Length(max = 20, message = ValidationMessages.MAX_ACRONYM_LENGTH)
        String acronym,

        @Length(max = 100, message = ValidationMessages.MAX_ORIGIN_LENGTH)
        String origin
) {
}
