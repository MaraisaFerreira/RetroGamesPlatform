package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.AssertTrue;
import maraisaferreira.com.github.RetroGamePlatform.helpers.Messages;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.validator.constraints.Length;

public record ConsoleUpdateRequestDto(
        String name,

        @Length(max = 20, message = Messages.maxLength + "20 characters.")
        String acronym,

        @Length(max = 100, message = Messages.maxLength + "100 characters.")
        String origin
) {
    @AssertTrue(message = Messages.atLeastOneField)
    public boolean hasAnyData() {
        return Strings.isNotBlank(name)
                || Strings.isNotBlank(acronym)
                || Strings.isNotBlank(origin);
    }
}
