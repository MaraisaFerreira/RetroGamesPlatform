package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import maraisaferreira.com.github.RetroGamePlatform.config.AppConstants;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.errorMessages.ValidationMessages;

public record PlayerEmailRequestDto(

        @Email(regexp = AppConstants.EMAIL_PATTERN, message = ValidationMessages.NOT_A_VALID_EMAIL)
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String email
) {
}
