package maraisaferreira.com.github.RetroGamePlatform.service;

import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.PlayerRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.PlayerResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomBadRequestException;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.errorMessages.ExceptionMessages;
import maraisaferreira.com.github.RetroGamePlatform.model.Player;
import maraisaferreira.com.github.RetroGamePlatform.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Transactional
    public PlayerResponseDto savePlayer(PlayerRequestDto requestDto) {
        playerRepository.findByEmail(requestDto.email())
                .ifPresent(present -> {
                    throw new CustomBadRequestException(ExceptionMessages.getUniqueFieldMessage("Email"));
                });

        Player player = playerRepository.save(new Player(
                requestDto.name(),
                requestDto.email(),
                requestDto.age(),
                requestDto.password()
        ));

        return new PlayerResponseDto(player);
    }
}
