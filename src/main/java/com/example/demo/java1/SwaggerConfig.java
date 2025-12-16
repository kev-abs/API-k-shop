package com.example.demo.java1;

import io.swagger.v3.oas.models.OpenAPI;                      // PPT
import io.swagger.v3.oas.models.info.Info;                   // PPT
import io.swagger.v3.oas.models.security.SecurityRequirement;// PPT
import io.swagger.v3.oas.models.security.SecurityScheme;     // PPT
import io.swagger.v3.oas.models.Components;                  // PPT

import org.springframework.context.annotation.Bean;          // PPT
import org.springframework.context.annotation.Configuration; // PPT

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDoc() {

        return new OpenAPI()

                .info(new Info()
                        .title("API K-SHOP")
                        .description("Documentación generada automáticamente con Swagger")
                        .version("1.0")
                )

                .addSecurityItem(
                        new SecurityRequirement().addList("BearerAuth")
                )

                .components(
                        new Components().addSecuritySchemes(
                                "BearerAuth",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}
