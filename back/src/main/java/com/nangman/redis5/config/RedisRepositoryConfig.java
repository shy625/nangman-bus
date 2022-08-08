package com.nangman.redis5.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@RequiredArgsConstructor
@Configuration
@EnableRedisRepositories
//public class RedisRepositoryConfig implements LettuceClientConfigurationBuilderCustomizer {
public class RedisRepositoryConfig {
    /*
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;
    */

    private final RedisProperties redisProperties;

    //Lettuce
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        lettuceConnectionFactory.setHostName(redisProperties.getHost());
        lettuceConnectionFactory.setPort(redisProperties.getPort());
        lettuceConnectionFactory.setPassword(redisProperties.getPassword());
        return lettuceConnectionFactory;

//        return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
//        return new LettuceConnectionFactory()
    }

//    @Bean
//    public RedisTemplate<?, ?> redisTemplate() {
//        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        return redisTemplate;
//    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
       RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
       redisTemplate.setConnectionFactory(redisConnectionFactory());
       redisTemplate.setKeySerializer(new StringRedisSerializer());
       return redisTemplate;
    }
//        @Bean
//        public RedisTemplate<?, ?> redisTemplate() {
//            RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
//
//            redisTemplate.setDefaultSerializer(new StringRedisSerializer());
//
//            redisTemplate.setConnectionFactory(redisConnectionFactory());
//            redisTemplate.afterPropertiesSet();
//            return redisTemplate;
//        }
//            @Bean
//            public RedisTemplate<?, ?> redisTemplate() {
//                RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
//                redisTemplate.setConnectionFactory(redisConnectionFactory());
//                redisTemplate.setKeySerializer(new StringRedisSerializer());
//                redisTemplate.setValueSerializer(new StringRedisSerializer());
//
//                redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//                redisTemplate.setHashValueSerializer(new StringRedisSerializer());
//
//                return redisTemplate;
//            }
//        @Bean
//        public RedisTemplate<String, Object> redisTemplate() {
//            RedisTemplate<String, Object> template = new RedisTemplate<>();
//            template.setConnectionFactory(redisConnectionFactory());
//            return template;
//        }
//    @Override
//    public void customize(LettuceClientConfiguration.LettuceClientConfigurationBuilder clientConfigurationBuilder) {
//        clientConfigurationBuilder.clientOptions(ClientOptions.builder()
//                        .protocolVersion(ProtocolVersion.RESP2)
//                        .build());
//    }
//    @Bean
//    RedisConnectionFactory redisConnectionFactory(RedisProperties props) {
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//
//        config.setPassword(props.getPassword());
//
//        return new LettuceConnectionFactory(config);
//    }

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }
}
