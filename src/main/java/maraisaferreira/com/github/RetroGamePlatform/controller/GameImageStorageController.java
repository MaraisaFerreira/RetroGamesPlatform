package maraisaferreira.com.github.RetroGamePlatform.controller;

import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.GameResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.service.GameImageStorageService;
import maraisaferreira.com.github.RetroGamePlatform.service.GameService;
import maraisaferreira.com.github.RetroGamePlatform.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cover")
public class GameImageStorageController {
    private final GameImageStorageService imageStorageService;

    @PostMapping("/{id}/save")
    public ResponseEntity<GameResponseDto> saveGameCover(@PathVariable Long id,
                                                         @RequestParam(name = "file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                imageStorageService.saveGameCover(id, file)
        );
    }
}
