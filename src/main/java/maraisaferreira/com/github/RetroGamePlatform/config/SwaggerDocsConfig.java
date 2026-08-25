package maraisaferreira.com.github.RetroGamePlatform.config;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import maraisaferreira.com.github.RetroGamePlatform.constants.AppConstants;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.ExceptionResponseDto;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerDocsConfig {

    @Bean
    OpenAPI mainDocsConfig() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI().info(new Info()
                .title("Retro Games Platform \uD83C\uDFAE")
                .version("v1")
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0"))
                .description(String.format("A platform for preserving and revisiting the best classic games. <br>" +
                        "Only games released between %s and %s on the best video game consoles available at the " +
                        "time.", AppConstants.LOWEST_GAME_YEAR, AppConstants.HIGHEST_GAME_YEAR))
                .contact(new Contact()
                        .name("Maraísa Ferreira")
                        .url("https://github.com/MaraisaFerreira/RetroGamesPlatform")
                )


                ).addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                );
    }

    @Bean
    OpenApiCustomizer setGlobalResponsesSchema() {
        Schema<?> schema = ModelConverters.getInstance()
                .readAllAsResolvedSchema(ExceptionResponseDto.class).schema;

        Content content = new Content().addMediaType("application/json",
                new MediaType().schema(schema));

        return openApi -> openApi.getPaths().values().forEach(path ->
                path.readOperations().forEach(operation -> {
                            ApiResponses responses = operation.getResponses();

                            responses.addApiResponse("400", new ApiResponse()
                                    .description("Bad Request: Invalid request data.")
                                    .content(content)
                            );

                            responses.addApiResponse("500", new ApiResponse()
                                    .description("Internal Server Error: Something went wrong.")
                                    .content(content)
                            );


                            if (operation.getOperationId().contains("ById") ||
                                    operation.getOperationId().contains("update") ||
                                    operation.getOperationId().contains("delete") ||
                                    operation.getOperationId().contains("add") ||
                                    operation.getOperationId().contains("remove")
                            ) {
                                responses.addApiResponse("404", new ApiResponse()
                                        .description("Not Found: The resource does not exist.")
                                        .content(content));
                            }
                        }
                )
        );
    }

}
