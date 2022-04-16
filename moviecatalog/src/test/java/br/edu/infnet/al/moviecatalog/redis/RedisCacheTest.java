package br.edu.infnet.al.moviecatalog.redis;

import br.edu.infnet.al.moviecatalog.model.Movie;
import org.junit.jupiter.api.*;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import redis.clients.jedis.Jedis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Testcontainers
public class RedisCacheTest {
    private static GenericContainer genericContainer;
    private RedisCache redisCache;

    @BeforeAll
    static void init(){
        genericContainer = new GenericContainer(DockerImageName.parse("redis:6.2.6"))
                .withExposedPorts(6379);
        genericContainer.start();
    }

    @BeforeEach
    void setUp(){
        Jedis jedis = new Jedis("localhost", genericContainer.getMappedPort(6379));
        redisCache = new RedisCache(jedis);
        jedis.flushAll();
    }

    @Test
    @DisplayName("When release date is within one month should insert in redis")
    void testInsertRealiseDateThisMonth_thenShouldInsert(){
        Movie movie = new Movie();
        movie.setName("movie1");
        movie.setReleaseDate(LocalDate.now().minusDays(10).format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(movie.getReleaseDate());
        this.redisCache.insert(movie);
        List<String> newReleases = this.redisCache.getNewReleases();
        Assertions.assertTrue(newReleases.contains("movie1"));

    }
    @Test
    @DisplayName("When release date isn't within one month shouldn't insert in redis")
    void testInsertRealiseDateNotThisMonth_thenShouldNotInsert(){
        Movie movie = new Movie();
        movie.setName("movie1");
        movie.setReleaseDate(LocalDate.now().minusDays(35).format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(movie.getReleaseDate());
        this.redisCache.insert(movie);
        List<String> newReleases = this.redisCache.getNewReleases();
        Assertions.assertFalse(newReleases.contains("movie1"));
    }

    @AfterAll
    static void destroy(){
        genericContainer.stop();
    }
}
