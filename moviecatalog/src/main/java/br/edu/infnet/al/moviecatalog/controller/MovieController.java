package br.edu.infnet.al.moviecatalog.controller;

import br.edu.infnet.al.moviecatalog.model.Movie;
import br.edu.infnet.al.moviecatalog.service.MovieService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.SplittableRandom;
import java.util.UUID;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/movie")
public class MovieController {
  private final MovieService service;
  Counter getByIdFound;
  Counter getByIdNotFound;
  Timer timerDatabase;
  Timer timerRedis;

  @Autowired
  public MovieController(MeterRegistry meterRegistry, MovieService service){
    this.service = service;
    getByIdFound = Counter.builder("get_by_id_found").register(meterRegistry);
    getByIdNotFound = Counter.builder("get_by_id_not_found").register(meterRegistry);
  }

  @GetMapping
  public ResponseEntity<List<Movie>> getAll(){
    randomRuntimeError();
    var movies = service.getAll();
    return ResponseEntity.ok().body(movies);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movie> getById(@PathVariable String id){
    randomRuntimeError();
    var movie = service.getById(id);
    return movie.map(this::returnOk).orElseGet(this::returnNotFound);

  }

  private ResponseEntity<Movie> returnNotFound() {
    getByIdNotFound.increment();
    return ResponseEntity.notFound().build();
  }

  private ResponseEntity<Movie> returnOk(Movie movie) {
    getByIdFound.increment();
    return ResponseEntity.ok().body(movie);
  }

  @GetMapping("/newreleases")
  public ResponseEntity<List<String>> getNewReleases(){
    randomRuntimeError();
    var movies = service.getNewReleases();
    return ResponseEntity.ok().body(movies);
  }

  @PostMapping
  public ResponseEntity<Movie> create(@RequestBody Movie movie){
    randomRuntimeError();
    return new ResponseEntity<>(service.save(movie), HttpStatus.CREATED);
  }

  private void randomRuntimeError(){
    SplittableRandom random = new SplittableRandom();
    int i = random.nextInt(1000);
    if(i > 900){
      log.error("randomRuntimeError");
      throw new RuntimeException("Um Erro Ocorreu");
    }
  }
}
