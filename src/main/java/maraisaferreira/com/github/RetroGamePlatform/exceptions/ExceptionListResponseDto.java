package maraisaferreira.com.github.RetroGamePlatform.exceptions;

import java.time.Instant;
import java.util.List;

public record ExceptionListResponseDto(
        Instant dateTime,
        List<String> messages,
        String path
) {
}
