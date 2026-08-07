package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.*;

import java.util.List;

public record GameRequestDto(
        @NotBlank(message = "This field cannot be null or empty.")
        String name,

        @NotNull(message = "This field cannot be null.")
        @Min(value = 1970, message = "The lower value allowed is 1970.")
        @Max(value = 2010, message = "The maximum value allowed is 2010.")
        Integer releaseYear,

        String gameType,

        @NotNull(message = "This field cannot be null.")
        @Size(min = 1, message = "The game must belong to at least one console.")
        List<Long> consoleIds
) {
}
