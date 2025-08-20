package org.purpura.apimg.config.jackson;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Module blankToNullStringModule() {
        SimpleModule module = new SimpleModule("blankToNullString");
        module.addDeserializer(String.class, new BlankToNullStringDeserializer());
        return module;
    }
}