package br.edu.infnet.al.moviecatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class JedisConfig {
    @Bean
    public Jedis jedis(){
        Jedis jedis = new Jedis("redis", 6379);
        jedis.auth("Redis2022!");
        jedis.flushAll();
        return jedis;
    }
}
