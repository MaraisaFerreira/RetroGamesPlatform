package maraisaferreira.com.github.RetroGamePlatform.service;

import lombok.extern.slf4j.Slf4j;
import maraisaferreira.com.github.RetroGamePlatform.config.StorageConfig;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomBadRequestException;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomInternalServerErrorException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Objects;

@Slf4j
@Service
public class StorageService {
    private final Path dirPath;

    public StorageService(StorageConfig config) {
        dirPath = Path.of(config.getDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(dirPath);
            log.info("Folder created.");
        } catch (Exception ex) {
            throw new CustomInternalServerErrorException("Folder could not be created. " + ex.getMessage());
        }
    }

    public void uploadFile(MultipartFile file) {
        Path fileUrl = getFileUrl(file);

        try {
            Files.copy(file.getInputStream(), fileUrl, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new CustomInternalServerErrorException("Ops, something gone wrong. Try again later.");
        }
    }

    public Path getFileUrl(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new CustomBadRequestException("Image cannot be empty or null.");
        }

        if (!Objects.equals(file.getContentType(), "image/jpeg")
                && !Objects.equals(file.getContentType(), "image/png")) {
            throw new CustomBadRequestException("Just are allowed jpeg or png images.");
        }

        String fileName = file.getOriginalFilename() != null && !file.getOriginalFilename().isBlank() ?
                file.getOriginalFilename() : "file:" + Instant.now().toEpochMilli();

        if (fileName.contains("..")) {
            throw new CustomBadRequestException("Image name cannot contain ..");
        }

        return dirPath.resolve(fileName).toAbsolutePath().normalize();
    }

    public void deleteOldCover(String prevCoverUrl) {
        try {
            Files.deleteIfExists(Path.of(prevCoverUrl));
        } catch (Exception ex) {
            log.warn("The cover {} wasn't deleted.", prevCoverUrl);
        }
    }
}
