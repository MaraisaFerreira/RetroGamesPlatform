package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.AssertTrue;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.validator.constraints.Length;

public record ConsoleUpdateRequestDto(
        String name,

        @Length(max = 20, message = "The maximum length is 20 characters.")
        String acronym,

        @Length(max = 100, message = "The maximum length is 100 characters.")
        String origin
) {
    @AssertTrue(message = "No data to update. At least one field must be filled.")
    public boolean hasAnyData() {
        return Strings.isNotBlank(name)
                || Strings.isNotBlank(acronym)
                || Strings.isNotBlank(origin);
    }
}
