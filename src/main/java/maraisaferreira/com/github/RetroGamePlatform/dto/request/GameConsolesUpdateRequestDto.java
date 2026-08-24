package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ValidationMessages;

import java.util.List;

public record GameConsolesUpdateRequestDto(
        @NotNull(message = ValidationMessages.SEND_AT_LEAST_ONE_ID)
        @Size(min = 1, message = ValidationMessages.SEND_AT_LEAST_ONE_ID)
        List<Long> consolesIDs
) {
}
