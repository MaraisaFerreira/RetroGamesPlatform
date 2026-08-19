package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import maraisaferreira.com.github.RetroGamePlatform.config.AppConstants;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.errorMessages.ValidationMessages;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.Period;

public record PlayerRequestDto(
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String name,

        @NotNull(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        LocalDate birthDate,

        @Pattern(regexp = AppConstants.EMAIL_PATTERN, message = ValidationMessages.NOT_A_VALID_EMAIL)
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String email,

        @Length(min = 5, message = ValidationMessages.MIN_LENGTH_ALLOWED + "5 chars")
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String password
) {
    @AssertTrue(message = ValidationMessages.INVALID_DATE)
    public boolean getBirthDateValid() {
        return birthDate != null && Period.between(this.birthDate, LocalDate.now()).getYears() >= 10;
    }
}
