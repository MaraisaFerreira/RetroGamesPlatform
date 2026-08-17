package maraisaferreira.com.github.RetroGamePlatform.dto.response;

import maraisaferreira.com.github.RetroGamePlatform.model.Player;

public record PlayerResponseDto(
        String id,
        String name,
        String email,
        Integer age
) {
    public PlayerResponseDto(Player player) {
        this(
                player.getId().toString(),
                player.getName(),
                player.getEmail(),
                player.getAge()
        );
    }
}
