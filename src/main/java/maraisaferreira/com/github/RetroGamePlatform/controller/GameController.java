package maraisaferreira.com.github.RetroGamePlatform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.GameRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.GameUpdateRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.MessageGameSavedResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.GameResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/games")
public class GameController {
    private final GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameResponseDto>> findAllGames() {
        return ResponseEntity.ok(gameService.findAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDto> findGameById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.findGameById(id));
    }

    @PostMapping
    public ResponseEntity<MessageGameSavedResponseDto> saveGame(@Valid @RequestBody GameRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                gameService.saveGame(requestDto)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GameResponseDto> updateGame(@PathVariable Long id,
                                                      @Valid @RequestBody GameUpdateRequestDto requestDto) {
        return ResponseEntity.ok(gameService.updateGame(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);

        return ResponseEntity.noContent().build();
    }
}
