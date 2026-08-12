package maraisaferreira.com.github.RetroGamePlatform.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class StorageConfig {

    @Value("${storage.dir}")
    private String dir;
}
