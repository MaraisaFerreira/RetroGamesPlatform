package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.NotBlank;
import maraisaferreira.com.github.RetroGamePlatform.messages.MessagesCenter;
import org.hibernate.validator.constraints.Length;

public record ConsoleRequestDto(
        @NotBlank(message = MessagesCenter.notNullOrEmpty)
        String name,

        @Length(max = 20, message = MessagesCenter.maxLength + "20 characters.")
        String acronym,

        @Length(max = 100, message = MessagesCenter.maxLength + "100 characters.")
        String origin
) {
}
