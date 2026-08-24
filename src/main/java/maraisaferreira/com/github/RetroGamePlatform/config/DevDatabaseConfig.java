package maraisaferreira.com.github.RetroGamePlatform.config;

import org.springframework.boot.flyway.autoconfigure.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class DevDatabaseConfig {

//    @Bean
//    FlywayMigrationStrategy cleanDatabase() {
//        return flyway -> {
//            flyway.clean();
//
//            flyway.migrate();
//        };
//    }
}
