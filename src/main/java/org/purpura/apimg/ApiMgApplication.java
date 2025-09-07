package org.purpura.apimg;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiMgApplication {
    public static void main(String[] args) {
        // Carrega as variáveis de ambiente do arquivo .env
        Dotenv.load();

        SpringApplication.run(ApiMgApplication.class, args);
    }
}
