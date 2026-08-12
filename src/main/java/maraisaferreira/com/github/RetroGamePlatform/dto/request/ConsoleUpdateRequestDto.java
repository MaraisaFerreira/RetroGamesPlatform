package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.AssertTrue;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ValidationMessages;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.validator.constraints.Length;

public record ConsoleUpdateRequestDto(
        String name,

        @Length(max = 20, message = ValidationMessages.maxLength + "20 characters.")
        String acronym,

        @Length(max = 100, message = ValidationMessages.maxLength + "100 characters.")
        String origin
) {
    @AssertTrue(message = ValidationMessages.atLeastOneField)
    public boolean hasAnyData() {
        return Strings.isNotBlank(name)
                || Strings.isNotBlank(acronym)
                || Strings.isNotBlank(origin);
    }
}
