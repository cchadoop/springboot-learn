package com.ontop.config;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

	private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

	// 自定义key生成器
	@Bean
	public KeyGenerator keyGenerator() {
		return (o, method, params) -> {
			StringBuilder sb = new StringBuilder();
			sb.append(o.getClass().getName()); // 类名
			sb.append(method.getName()); // 方法名
			for (Object param : params) {
				sb.append(param.toString()); // 参数名
			}
			logger.info("自定义key生成器");
			return sb.toString();
		};
	}

	// 配置缓存管理器
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(10000)) // 60s缓存失效
				// 设置Key的序列化方式
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
				// 设置Value的序列化方式
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
				// 不缓存null值
				.disableCachingNullValues();
		RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory).cacheDefaults(config)
				.transactionAware().build();
		logger.info("自定义RedisCacheManager加载完成");
		return redisCacheManager;
	}

	private GenericJackson2JsonRedisSerializer valueSerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}

	private RedisSerializer<String> keySerializer() {
		return new StringRedisSerializer();
	}

	// redisTemplate 相关配置
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(keySerializer());
		redisTemplate.setValueSerializer(valueSerializer());
		redisTemplate.setHashKeySerializer(keySerializer());
		redisTemplate.setHashValueSerializer(valueSerializer());
		logger.info("序列化完成！");
		return redisTemplate;
	}
}
