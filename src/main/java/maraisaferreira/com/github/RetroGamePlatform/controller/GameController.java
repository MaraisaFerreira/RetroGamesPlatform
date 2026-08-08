package maraisaferreira.com.github.RetroGamePlatform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.GameConsolesUpdateRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.GameRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.GameUpdateRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.GameResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.GameSaveResponseDto;
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

    @GetMapping("/part_name/{part}")
    public ResponseEntity<List<GameResponseDto>> findGameByPartName(@PathVariable String part) {
        return ResponseEntity.ok(gameService.findGameByPartName(part));
    }

    @GetMapping("/by_type/{type}")
    public ResponseEntity<List<GameResponseDto>> findGameByGameType(@PathVariable String type) {
        return ResponseEntity.ok(gameService.findGameByGameType(type));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDto> findGameById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.findGameById(id));
    }

    @PostMapping
    public ResponseEntity<GameSaveResponseDto> saveGame(@Valid @RequestBody GameRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                gameService.saveGame(requestDto)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GameResponseDto> updateGame(@PathVariable Long id,
                                                      @Valid @RequestBody GameUpdateRequestDto requestDto) {
        return ResponseEntity.ok(gameService.updateGame(id, requestDto));
    }


    @PatchMapping("/{id}/add_consoles")
    public ResponseEntity<GameSaveResponseDto> addGameConsoles(@PathVariable Long id,
                                                               @Valid @RequestBody GameConsolesUpdateRequestDto requestDto) {
        return ResponseEntity.ok(gameService.addGameConsoles(id, requestDto));
    }

    @PatchMapping("/{id}/remove_consoles")
    public ResponseEntity<GameSaveResponseDto> removeGameConsoles(@PathVariable Long id,
                                                                  @Valid @RequestBody GameConsolesUpdateRequestDto requestDto) {
        return ResponseEntity.ok(gameService.removeGameConsoles(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);

        return ResponseEntity.noContent().build();
    }
}
