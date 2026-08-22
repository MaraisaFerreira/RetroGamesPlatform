package maraisaferreira.com.github.RetroGamePlatform.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.GameResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.service.GameImageStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Game Cover")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cover")
public class GameImageStorageController {
    private final GameImageStorageService imageStorageService;

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping(
            value = "/{id}/save",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ResponseEntity<GameResponseDto> saveGameCover(@PathVariable Long id,
                                                         @RequestParam(name = "file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                imageStorageService.saveGameCover(id, file)
        );
    }

    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/game/{id}")
    public ResponseEntity<Void> removeGameCover(@PathVariable Long id){
        imageStorageService.removeGameCover(id);
        return ResponseEntity.noContent().build();
    }
}
