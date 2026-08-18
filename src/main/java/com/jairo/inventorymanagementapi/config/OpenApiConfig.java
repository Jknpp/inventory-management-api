package com.jairo.inventorymanagementapi.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI inventoryManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inventory Management API")
                        .description("REST API para gerenciamento de estoque desenvolvida com Spring Boot.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Jairo Karnopp")
                                .email("jairoknp@email.com")
                                .url("https://github.com/Jknpp"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório no GitHub")
                        .url("https://github.com/Jknpp/inventory-management-api"));
    }
}