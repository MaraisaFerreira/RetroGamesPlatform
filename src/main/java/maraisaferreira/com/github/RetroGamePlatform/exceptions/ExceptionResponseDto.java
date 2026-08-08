package maraisaferreira.com.github.RetroGamePlatform.exceptions;

import java.time.Instant;

public record ExceptionResponseDto(
        Instant dateTime,
        String message,
        String path
) {
}
