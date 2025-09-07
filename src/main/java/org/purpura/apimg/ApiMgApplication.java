package org.purpura.apimg;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiMgApplication {
    // Carrega as variáveis de ambiente do arquivo .env
    Dotenv dotenv = Dotenv.load();
    public static void main(String[] args) {
        SpringApplication.run(ApiMgApplication.class, args);
    }
}
