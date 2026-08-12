package maraisaferreira.com.github.RetroGamePlatform.service;

import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.GameResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class GameImageStorageService {
    private final StorageService storageService;
    private final GameService gameService;


    public GameResponseDto saveGameCover(Long id, MultipartFile file) {
        String prevCoverUrl = gameService.getGameCoverUrl(id);
        String newCoverUrl = storageService.getFileUrl(file).toString();

        if (!Objects.equals(prevCoverUrl, newCoverUrl)){
            storageService.uploadFile(file);
            GameResponseDto gameDto = gameService.addCover(id, newCoverUrl);
            storageService.deleteOldCover(prevCoverUrl);

            return gameDto;
        }
        return null;
    }
}
