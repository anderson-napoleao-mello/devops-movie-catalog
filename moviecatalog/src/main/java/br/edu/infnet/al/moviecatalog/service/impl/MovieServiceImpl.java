package br.edu.infnet.al.moviecatalog.service.impl;

import br.edu.infnet.al.moviecatalog.model.Movie;
import br.edu.infnet.al.moviecatalog.redis.RedisCache;
import br.edu.infnet.al.moviecatalog.repository.MovieRepository;
import br.edu.infnet.al.moviecatalog.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
@Service
public class MovieServiceImpl implements MovieService {
  private final MovieRepository repository;
  private final RedisCache redis;

  @Override
  public List<Movie> getAll() {
    log.info("getAll");
    return repository.findAll();
  }

  @Override
  public Optional<Movie> getById(String id) {
    log.info("getById {}", id);
    return repository.findById(id);
  }

  @Override
  public List<String> getNewReleases() {
    log.info("getNewReleases");
    return redis.getNewReleases();
  }

  @Override
  public Movie save(Movie movie) {
    log.info("save movie {}", movie.toString());
    var saved = repository.save(movie);
    redis.insert(saved);
    return saved;
  }
}
