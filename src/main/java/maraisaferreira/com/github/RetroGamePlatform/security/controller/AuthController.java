package maraisaferreira.com.github.RetroGamePlatform.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.PlayerRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.PlayerResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final PlayerService playerService;

    @PostMapping("/register")
    public ResponseEntity<PlayerResponseDto> registerUser(@Valid @RequestBody PlayerRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                playerService.savePlayer(requestDto)
        );
    }
}
