package maraisaferreira.com.github.RetroGamePlatform.service;

import lombok.extern.slf4j.Slf4j;
import maraisaferreira.com.github.RetroGamePlatform.config.StorageConfig;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.StorageMessages;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomBadRequestException;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomInternalServerErrorException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
public class StorageService {
    private final Path dirPath;
    private static final List<String> IMAGE_TYPES = List.of("image/jpeg", "image/png");

    public StorageService(StorageConfig config) {
        dirPath = Path.of(config.getDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(dirPath);
            log.info("Folder created.");
        } catch (IOException ex) {
            throw new CustomInternalServerErrorException(StorageMessages.FOLDER_NOT_CREATED);
        }
    }

    public void uploadFile(String fileName, MultipartFile file) {
        Path filePath = dirPath.resolve(fileName).toAbsolutePath().normalize();

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new CustomInternalServerErrorException(StorageMessages.SOMETHING_WRONG);
        }
    }

    public void deleteOldCover(String fileName) {
        if (Strings.isNotBlank(fileName)) {
            Path filePath = dirPath.resolve(fileName).toAbsolutePath().normalize();
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException ex) {
                log.warn("The cover {} wasn't deleted.", fileName);
            }
        }
    }

    public String getValidImgName(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new CustomBadRequestException(StorageMessages.NOT_NULL_OR_EMPTY);
        }

        if (file.getContentType() == null || !IMAGE_TYPES.contains(file.getContentType())) {
            throw new CustomBadRequestException(StorageMessages.IMAGE_TYPES);
        }

        String fileName = Strings.isNotBlank(file.getOriginalFilename()) ?
                file.getOriginalFilename() : "file:" + Instant.now().toEpochMilli();

        if (fileName.contains("..")) {
            throw new CustomBadRequestException(StorageMessages.INVALID_NAME);
        }

        return fileName;
    }

}
