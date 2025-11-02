package org.purpura.apimg.config.redis;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.jspecify.annotations.NonNull;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Bean
    public RedisCacheConfiguration defaultCacheConfig() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY
        );
        GenericJackson2JsonRedisSerializer jacksonSerializer = new GenericJackson2JsonRedisSerializer(mapper);
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jacksonSerializer))
                .disableCachingNullValues();
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration defaultConfig = defaultCacheConfig();

        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();
        cacheConfigs.put("empresa", defaultConfig.entryTtl(Duration.ofMinutes(60)));
        cacheConfigs.put("endereco", defaultConfig.entryTtl(Duration.ofMinutes(60)));
        cacheConfigs.put("chavePix", defaultConfig.entryTtl(Duration.ofMinutes(60)));
        cacheConfigs.put("residuo", defaultConfig.entryTtl(Duration.ofMinutes(60)));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfigs)
                .build();
    }

    @Bean
    public CacheErrorHandler cacheErrorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(@NonNull RuntimeException exception, @NonNull Cache cache, @NonNull Object key) {
                System.err.println("[REDIS LOG] Cache GET error for key=" + key + ", cache=" + cache.getName() + ": " + exception.getMessage());
                Throwable cause = exception.getCause();
                if (exception instanceof SerializationException || (cause instanceof SerializationException)) {
                    try {
                        cache.evict(key);
                    } catch (Exception e) {
                        System.err.println("[REDIS LOG] Failed to evict cache key after serialization error: " + e.getMessage());
                    }
                }
            }

            @Override
            public void handleCachePutError(@NonNull RuntimeException exception, @NonNull Cache cache, @NonNull Object key, Object value) {
                System.err.println("[REDIS LOG] Cache PUT error for key=" + key + ": " + exception.getMessage());
            }

            @Override
            public void handleCacheEvictError(@NonNull RuntimeException exception, @NonNull Cache cache, @NonNull Object key) {
                System.err.println("[REDIS LOG] Cache EVICT error for key=" + key + ": " + exception.getMessage());
            }

            @Override
            public void handleCacheClearError(@NonNull RuntimeException exception, @NonNull Cache cache) {
                System.err.println("[REDIS LOG] Cache CLEAR error for cache=" + (cache != null ? cache.getName() : "null") + ": " + exception.getMessage());
            }
        };
    }
}
