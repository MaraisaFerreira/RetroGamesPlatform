package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.NotBlank;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ValidationMessages;
import org.hibernate.validator.constraints.Length;

public record ConsoleRequestDto(
        @NotBlank(message = ValidationMessages.notNullOrEmpty)
        String name,

        @Length(max = 20, message = ValidationMessages.maxLength + "20 characters.")
        String acronym,

        @Length(max = 100, message = ValidationMessages.maxLength + "100 characters.")
        String origin
) {
}
