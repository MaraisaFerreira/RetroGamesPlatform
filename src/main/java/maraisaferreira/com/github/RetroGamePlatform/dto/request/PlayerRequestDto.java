package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import maraisaferreira.com.github.RetroGamePlatform.constants.AppConstants;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ValidationMessages;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.Period;

public record PlayerRequestDto(
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String name,

        @NotNull(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        LocalDate birthDate,

        @Email(regexp = AppConstants.EMAIL_REGEX, message = ValidationMessages.NOT_A_VALID_EMAIL)
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String email,

        @Length(min = 5, message = ValidationMessages.MIN_PASSWORD_LENGTH)
        @NotBlank(message = ValidationMessages.NOT_NULL_OR_EMPTY)
        String password
) {
    @AssertTrue(message = ValidationMessages.INVALID_AGE)
    public boolean getBirthDateValid() {
        return birthDate != null && Period.between(this.birthDate, LocalDate.now()).getYears() >= 10;
    }
}
