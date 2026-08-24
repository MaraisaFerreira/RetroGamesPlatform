package maraisaferreira.com.github.RetroGamePlatform.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.PlayerEmailRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.PlayerRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.PlayerResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Players")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {
    private final PlayerService playerService;

    @PreAuthorize("hasRole('BASIC')")
    @GetMapping("/email")
    public ResponseEntity<PlayerResponseDto> findPlayerByEmail(
            @Valid @RequestBody PlayerEmailRequestDto requestDto) {
        return ResponseEntity.ok(playerService.findPlayerByEmail(requestDto));
    }

    @PreAuthorize("hasRole('BASIC')")
    @PostMapping
    public ResponseEntity<PlayerResponseDto> savePlayer(@Valid @RequestBody PlayerRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                playerService.savePlayer(requestDto)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> removePlayer(@Valid @RequestBody PlayerEmailRequestDto requestDto) {
        playerService.removePlayer(requestDto);

        return ResponseEntity.noContent().build();
    }
}
