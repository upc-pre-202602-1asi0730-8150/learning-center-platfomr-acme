package com.tesla.platform.u202113310.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI (Swagger UI) configuration. Security is intentionally not enabled.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@Configuration
public class OpenApiConfiguration {

    @Value("${spring.application.name}")
    String applicationName;

    @Value("${documentation.application.description}")
    String applicationDescription;

    @Value("${documentation.application.version}")
    String applicationVersion;

    @Bean
    public OpenAPI teslaFleetOpenApi() {
        return new OpenAPI().info(new Info()
                .title(applicationName)
                .description(applicationDescription)
                .version(applicationVersion));
    }
}
