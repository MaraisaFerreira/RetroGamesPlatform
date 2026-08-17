package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.*;
import maraisaferreira.com.github.RetroGamePlatform.config.AppConstants;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.errorMessages.ValidationMessages;
import org.hibernate.validator.constraints.Length;

public record PlayerRequestDto(
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String name,

        @Pattern(regexp = AppConstants.EMAIL_PATTERN, message = ValidationMessages.NOT_A_VALID_EMAIL)
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String email,

        @Length(min = 5, message = ValidationMessages.MIN_LENGTH_ALLOWED + "5 chars")
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String password,

        @Max(value = 100, message = ValidationMessages.MAX_VALUE_ALLOWED + "100")
        @Min(value = 5, message = ValidationMessages.LOWER_VALUE_ALLOWED + "5")
        @NotNull(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        Integer age
) {
}
