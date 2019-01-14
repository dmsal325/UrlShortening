package kr.or.winterdevcamp.urlshortening.config;

import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {
	
//	    
//	    private @Value("${spring.redis.host}") String redisHost;
//	    private @Value("${spring.redis.port}") int redisPort;
	 
	    @Bean
	    public JedisConnectionFactory connectionFactory() {                
	        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
	        jedisConnectionFactory.setHostName("localhost");
	        jedisConnectionFactory.setPort(6379);
	        jedisConnectionFactory.setUsePool(true);        
	        return jedisConnectionFactory;
	    }
	    
	    @Bean
	    public RedisTemplate<String, Object> redisTemplate() {
	        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
	        redisTemplate.setKeySerializer(new StringRedisSerializer());
	        redisTemplate.setValueSerializer(new StringRedisSerializer());
	        redisTemplate.setConnectionFactory(connectionFactory());        
	        return redisTemplate;
	    }

}
