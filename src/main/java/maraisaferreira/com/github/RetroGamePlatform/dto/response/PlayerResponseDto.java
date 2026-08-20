package maraisaferreira.com.github.RetroGamePlatform.dto.response;

import maraisaferreira.com.github.RetroGamePlatform.model.Player;
import maraisaferreira.com.github.RetroGamePlatform.model.enums.Roles;

import java.time.LocalDate;

public record PlayerResponseDto(
        String name,
        LocalDate birthDate,
        Integer age,
        String email,
        Roles role
) {
    public PlayerResponseDto(Player player) {
        this(
                player.getName(),
                player.getBirthDate(),
                player.getAge(),
                player.getEmail(),
                player.getSysRole()
        );
    }
}
