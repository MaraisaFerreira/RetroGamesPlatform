package maraisaferreira.com.github.RetroGamePlatform.security.service;

import maraisaferreira.com.github.RetroGamePlatform.model.enums.Roles;

public record TokenDataDto(
        String email,
        Roles roles
) {
}
