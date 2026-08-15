package maraisaferreira.com.github.RetroGamePlatform.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerDocsConfig {

    @Bean
    OpenAPI mainDocsConfig(){
        return new OpenAPI().info(new Info()
                .title("Retro Games Platform \uD83C\uDFAE")
                .version("v1")
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0"))
                .description("A platform for preserving and revisiting the best classic games. <br>" +
                        "Only games released between 1970 and 2010 on the best video game consoles available at the time.")
                .contact(new Contact()
                        .name("Maraísa Ferreira")
                        .url("https://github.com/MaraisaFerreira/RetroGamesPlatform"))
        );
    }

}
