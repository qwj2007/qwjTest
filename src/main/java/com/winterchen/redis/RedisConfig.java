package com.winterchen.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
@ConditionalOnClass({JedisConnection.class, RedisOperations.class, Jedis.class})
public class RedisConfig extends CachingConfigurerSupport {
	private static final Logger logger  = LoggerFactory.getLogger(RedisConfig.class);
	@Bean  
    public KeyGenerator wiselyKeyGenerator(){  
        return new KeyGenerator() {  
            @Override  
            public Object generate(Object target, Method method, Object... params) {  
                StringBuilder sb = new StringBuilder();
                sb.append("PAGE_DB_RESULT_CACHE_");
                sb.append(target.getClass().getSimpleName());  
                sb.append("."+method.getName());  
                for (Object obj : params) {  
                    sb.append(obj.toString());  
                } 
                logger.info("【缓存key】{}",sb.toString());
                return sb.toString();  
            }  
        };  
  
    }  
	@Bean
	public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
		RedisCacheManager manager = new RedisCacheManager(redisTemplate);
		manager.setDefaultExpiration(60*60); // 60s
		return manager;
	}

	@Bean
	@SuppressWarnings("rawtypes")
	public RedisSerializer fastJson2JsonRedisSerializer() {
		return new FastJson2JsonRedisSerializer<Object>(Object.class);
	}

	@Bean
	@SuppressWarnings("rawtypes")
	public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory factory, RedisSerializer fastJson2JsonRedisSerializer) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(template.getStringSerializer());
		template.setValueSerializer(fastJson2JsonRedisSerializer);
		template.setHashKeySerializer(template.getStringSerializer());
		template.setHashValueSerializer(fastJson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

}