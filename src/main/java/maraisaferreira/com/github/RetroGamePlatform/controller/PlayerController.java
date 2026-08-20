package maraisaferreira.com.github.RetroGamePlatform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.PlayerRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.PlayerResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {
    private final PlayerService playerService;

    @PreAuthorize("hasRole('BASIC')")
    @PostMapping
    public ResponseEntity<PlayerResponseDto> savePlayer(@Valid @RequestBody PlayerRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                playerService.savePlayer(requestDto)
        );
    }
}
