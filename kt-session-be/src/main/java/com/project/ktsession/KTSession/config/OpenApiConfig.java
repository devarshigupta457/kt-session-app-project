package com.project.ktsession.KTSession.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("KTSession API")
                        .version("1.0")
                        .description("REST APIs for KTSession")
                        .contact(new Contact()
                                .name("Deepak Kumar")
                                .email("support@ktsession.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("API Documentation"));
    }
}