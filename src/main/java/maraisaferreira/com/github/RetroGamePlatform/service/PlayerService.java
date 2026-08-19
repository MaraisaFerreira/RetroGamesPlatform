package maraisaferreira.com.github.RetroGamePlatform.service;

import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.PlayerRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.PlayerResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomBadRequestException;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomNotFoundException;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.errorMessages.ExceptionMessages;
import maraisaferreira.com.github.RetroGamePlatform.model.Player;
import maraisaferreira.com.github.RetroGamePlatform.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Transactional(readOnly = true)
    public PlayerResponseDto findPlayerByEmail(String email) {
        Player player = playerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.notFound("Email")));

        return new PlayerResponseDto(player);
    }

    @Transactional
    public PlayerResponseDto savePlayer(PlayerRequestDto requestDto) {
        playerRepository.findByEmail(requestDto.email())
                .ifPresent(present -> {
                    throw new CustomBadRequestException(ExceptionMessages.getUniqueFieldMessage("email"));
                });

        Player player = playerRepository.save(new Player(
                requestDto.name(),
                requestDto.birthDate(),
                requestDto.email(),
                requestDto.password()
        ));

        return new PlayerResponseDto(player);
    }

    @Transactional
    public void removePlayer(String email) {
        Player player = playerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.notFound("Email")));

        playerRepository.delete(player);
    }
}
