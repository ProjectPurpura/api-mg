package org.purpura.apimg.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "API MongoDB do PurPura",
                version = "v1",
                description = "Documentação da API",
                contact = @Contact(name = "PurPura Ambiental", email = "purpuraambiental4@gmail.com"),
                license = @License(name = "Apache-2.0")
        )
)
public class OpenApiConfig {}
