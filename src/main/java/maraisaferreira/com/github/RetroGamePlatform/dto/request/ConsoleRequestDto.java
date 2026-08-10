package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.NotBlank;
import maraisaferreira.com.github.RetroGamePlatform.helpers.Messages;
import org.hibernate.validator.constraints.Length;

public record ConsoleRequestDto(
        @NotBlank(message = Messages.notNullOrEmpty)
        String name,

        @Length(max = 20, message = Messages.maxLength + "20 characters.")
        String acronym,

        @Length(max = 100, message = Messages.maxLength + "100 characters.")
        String origin
) {
}
