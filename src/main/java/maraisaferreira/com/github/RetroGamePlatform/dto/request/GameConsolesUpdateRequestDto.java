package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ValidationMessages;

import java.util.List;

public record GameConsolesUpdateRequestDto(
        @NotNull(message = ValidationMessages.atLestOneId)
        @Size(min = 1, message = ValidationMessages.atLestOneId)
        List<Long> consolesIDs
) {
}
