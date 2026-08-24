package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.AssertTrue;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ValidationMessages;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.validator.constraints.Length;

public record ConsoleUpdateRequestDto(
        String name,

        @Length(max = 20, message = ValidationMessages.MAX_ACRONYM_LENGTH)
        String acronym,

        @Length(max = 100, message = ValidationMessages.MAX_ORIGIN_LENGTH)
        String origin
) {
    @AssertTrue(message = ValidationMessages.NO_DATA_TO_UPDATE)
    public boolean hasAnyData() {
        return Strings.isNotBlank(name)
                || Strings.isNotBlank(acronym)
                || Strings.isNotBlank(origin);
    }
}
