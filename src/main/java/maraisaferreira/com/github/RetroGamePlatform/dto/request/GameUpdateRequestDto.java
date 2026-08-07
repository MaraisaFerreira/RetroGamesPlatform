package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.apache.logging.log4j.util.Strings;

public record GameUpdateRequestDto(
        String name,

        @Min(value = 1970, message = "The lower value allowed is 1970.")
        @Max(value = 2010, message = "The maximum value allowed is 2010.")
        Integer releaseYear,

        String gameType
) {
    @AssertTrue(message = "Any data to update. At least one field must be send.")
    public boolean hasAnyField() {
        return Strings.isNotBlank(name)
                || Strings.isNotBlank(gameType)
                || releaseYear != null;
    }
}
