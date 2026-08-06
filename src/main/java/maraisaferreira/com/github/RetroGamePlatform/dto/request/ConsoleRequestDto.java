package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ConsoleRequestDto(
        @NotBlank(message = "This field cannot be null or empty.")
        String name,

        @Length(max = 20, message = "The maximum word length is 20 characters.")
        String acronym,

        @NotBlank(message = "This field cannot be null or empty.")
        @Length(max = 100, message = "The maximum word length is 100 characters.")
        String origin
) {
}
