package br.edu.infnet.al.moviecatalog.redis;

import br.edu.infnet.al.moviecatalog.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RedisCache implements Cache{

  private final Jedis jedis;

  public RedisCache(Jedis jedis){
    this.jedis = jedis;
  }

  @Override
  public void insert(Movie movie) {
    if(isNewRelease(movie.getReleaseDate())){
      put(movie.getName(),movie.getReleaseDate());
      log.info("Movie name inserted in cache [{} - {}]", movie.getName(), movie.getReleaseDate());
    }else {
      log.info("Movie [{} - {}] is not a new release so it was not inserted in cache", movie.getName(), movie.getReleaseDate());
    }
  }

  public void put(String key, String value) {
    jedis.set(key, value);
  }

  @Override
  public List<String> getNewReleases() {
    var movies = jedis.keys("*");
    deleteOld(movies);
    log.info("redis map: {}", movies);
    return getNewReleases(movies);
  }

  private void deleteOld(final Set<String> movies) {
    movies.stream()
      .filter(e -> !isNewRelease(jedis.get(e)))
      .peek(e->log.info("Movie {} {} is not a release anymore",e,jedis.get(e)))
      .forEach(jedis::del);
  }

  private List<String> getNewReleases(final Set<String> movies) {
    return movies.stream()
      .filter(e -> isNewRelease(jedis.get(e)))
      .collect(Collectors.toList());
  }

  private boolean isNewRelease(String date){
    LocalDate releaseDate = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
    LocalDate newRelease = LocalDate.now().minusMonths(1);
    log.info("isNewRelease: {} {} {}", releaseDate.isAfter(newRelease), releaseDate, newRelease);
    return releaseDate.isAfter(newRelease);
  }
}
