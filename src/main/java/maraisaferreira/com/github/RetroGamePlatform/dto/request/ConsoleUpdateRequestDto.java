package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.AssertTrue;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.errorMessages.ValidationMessages;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.validator.constraints.Length;

public record ConsoleUpdateRequestDto(
        String name,

        @Length(max = 20, message = ValidationMessages.MAX_LENGTH_ALLOWED + "20 characters.")
        String acronym,

        @Length(max = 100, message = ValidationMessages.MAX_LENGTH_ALLOWED + "100 characters.")
        String origin
) {
    @AssertTrue(message = ValidationMessages.NO_DATA_TO_UPDATE)
    public boolean hasAnyData() {
        return Strings.isNotBlank(name)
                || Strings.isNotBlank(acronym)
                || Strings.isNotBlank(origin);
    }
}
