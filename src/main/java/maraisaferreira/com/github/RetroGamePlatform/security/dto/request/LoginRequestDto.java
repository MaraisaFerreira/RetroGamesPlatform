package maraisaferreira.com.github.RetroGamePlatform.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.errorMessages.ValidationMessages;

public record LoginRequestDto(
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String email,

        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String password
) {
}
