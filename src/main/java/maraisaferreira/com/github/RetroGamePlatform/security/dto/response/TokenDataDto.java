package maraisaferreira.com.github.RetroGamePlatform.security.dto.response;

import maraisaferreira.com.github.RetroGamePlatform.model.enums.Roles;

public record TokenDataDto(
        String email,
        Roles roles
) {
}
