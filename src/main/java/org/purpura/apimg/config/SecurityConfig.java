package org.purpura.apimg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll()
            );
        return http.build();
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedPercent(true);   // allow encoded chars
        firewall.setAllowUrlEncodedSlash(true);     // allow %2F
        firewall.setAllowBackSlash(true);           // allow backslashes
        firewall.setAllowSemicolon(true);           // allow ; in URLs
        firewall.setAllowUrlEncodedPeriod(true);    // allow encoded dots
        return firewall;
    }
}

