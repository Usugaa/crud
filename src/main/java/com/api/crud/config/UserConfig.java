package com.api.crud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    // Configuración de OpenAPI para Swagger
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CRUD API Documentation")
                        .description("API documentation for the CRUD application")
                        .version("1.1")
                        .contact(new Contact()
                                .name("Carlos")
                                .email("carlos@gmail.com")
                        )
                );
    }
}