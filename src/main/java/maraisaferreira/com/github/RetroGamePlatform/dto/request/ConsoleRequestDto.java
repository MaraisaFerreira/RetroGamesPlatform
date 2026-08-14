package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.NotBlank;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.errorMessages.ValidationMessages;
import org.hibernate.validator.constraints.Length;

public record ConsoleRequestDto(
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String name,

        @Length(max = 20, message = ValidationMessages.MAX_LENGTH_ALLOWED + "20 characters.")
        String acronym,

        @Length(max = 100, message = ValidationMessages.MAX_LENGTH_ALLOWED + "100 characters.")
        String origin
) {
}
