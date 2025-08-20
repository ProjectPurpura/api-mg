package org.purpura.apimg.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "API MongoDB do Purpura",
                version = "v1",
                description = "Documentação da API",
                contact = @Contact(name = "Purpura Ambiental", email = "grupopurpura@gmail.com"),
                license = @License(name = "Apache-2.0")
        )
)
public class OpenApiConfig {}
