package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import maraisaferreira.com.github.RetroGamePlatform.messages.MessagesCenter;

import java.util.List;

public record GameConsolesUpdateRequestDto(
        @NotNull(message = MessagesCenter.atLestOneId)
        @Size(min = 1, message = MessagesCenter.atLestOneId)
        List<Long> consolesIDs
) {
}
