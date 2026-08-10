package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import maraisaferreira.com.github.RetroGamePlatform.helpers.Messages;

import java.util.List;

public record GameConsolesUpdateRequestDto(
        @NotNull(message = Messages.atLestOneId)
        @Size(min = 1, message = Messages.atLestOneId)
        List<Long> consolesIDs
) {
}
