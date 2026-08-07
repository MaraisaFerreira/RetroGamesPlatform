package maraisaferreira.com.github.RetroGamePlatform.dto.response;

import java.util.List;

public record MessageGameSavedResponseDto(
        String message,
        List<Long> errorsId,
        GameResponseDto game
) {
}
