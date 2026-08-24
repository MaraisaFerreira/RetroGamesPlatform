package maraisaferreira.com.github.RetroGamePlatform.service;

import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.PlayerEmailRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.PlayerRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.PlayerResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomBadRequestException;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomNotFoundException;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ExceptionMessages;
import maraisaferreira.com.github.RetroGamePlatform.model.Player;
import maraisaferreira.com.github.RetroGamePlatform.repositories.PlayerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public PlayerResponseDto findPlayerByEmail(PlayerEmailRequestDto requestDto) {
        Player player = playerRepository.findByEmail(requestDto.email())
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.UNIQUE_EMAIL));

        return new PlayerResponseDto(player);
    }

    @Transactional
    public PlayerResponseDto savePlayer(PlayerRequestDto requestDto) {
        playerRepository.findByEmail(requestDto.email())
                .ifPresent(present -> {
                    throw new CustomBadRequestException(ExceptionMessages.UNIQUE_EMAIL);
                });

        Player player = playerRepository.save(new Player(
                requestDto.name(),
                requestDto.birthDate(),
                requestDto.email(),
                passwordEncoder.encode(requestDto.password())
        ));

        return new PlayerResponseDto(player);
    }

    @Transactional
    public void removePlayer(PlayerEmailRequestDto requestDto) {
        Player player = playerRepository.findByEmail(requestDto.email())
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.EMAIL_NOT_FOUND));

        playerRepository.delete(player);
    }
}
