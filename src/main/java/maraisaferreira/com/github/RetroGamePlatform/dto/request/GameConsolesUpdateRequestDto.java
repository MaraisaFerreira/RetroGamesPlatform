package maraisaferreira.com.github.RetroGamePlatform.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record GameConsolesUpdateRequestDto(
        @NotNull(message = "You must send at least one ID.")
        @Size(min = 1, message = "You must send at least one ID.")
        List<Long> consolesIDs
) {
}
