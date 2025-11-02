package org.purpura.apimg.config.redis;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Bean
    public RedisCacheConfiguration defaultCacheConfig() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .disableCachingNullValues();
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration defaultConfig = defaultCacheConfig();
        // optional: per-cache overrides
        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();
        cacheConfigs.put("empresa", defaultConfig.entryTtl(Duration.ofMinutes(60)));
        cacheConfigs.put("empresas", defaultConfig.entryTtl(Duration.ofMinutes(10)));
        cacheConfigs.put("enderecos", defaultConfig.entryTtl(Duration.ofMinutes(10)));
        cacheConfigs.put("endereco", defaultConfig.entryTtl(Duration.ofMinutes(60)));
        cacheConfigs.put("chavesPix", defaultConfig.entryTtl(Duration.ofMinutes(10)));
        cacheConfigs.put("chavePix", defaultConfig.entryTtl(Duration.ofMinutes(60)));
        cacheConfigs.put("residuo", defaultConfig.entryTtl(Duration.ofMinutes(60)));
        cacheConfigs.put("residuos", defaultConfig.entryTtl(Duration.ofMinutes(10)));

        // build manager correctly (removed accidental 'return' before declaration)
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfigs);

        return builder.build();
    }
}