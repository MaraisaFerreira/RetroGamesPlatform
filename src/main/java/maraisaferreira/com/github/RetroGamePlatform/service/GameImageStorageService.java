package maraisaferreira.com.github.RetroGamePlatform.service;

import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.GameResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class GameImageStorageService {
    private final StorageService storageService;
    private final GameService gameService;


    public GameResponseDto saveGameCover(Long id, MultipartFile file) {
        String gameCover = gameService.getGameCover(id);
        String newGameCover = storageService.getValidImgName(file);

        if (!Objects.equals(gameCover, newGameCover)) {
            storageService.uploadFile(newGameCover, file);
            GameResponseDto gameDto = gameService.addCover(id, newGameCover);
            storageService.deleteOldCover(gameCover);

            return gameDto;
        }
        return null;
    }
}
