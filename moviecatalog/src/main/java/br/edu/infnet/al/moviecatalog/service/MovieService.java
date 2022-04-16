package br.edu.infnet.al.moviecatalog.service;

import br.edu.infnet.al.moviecatalog.model.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieService {
  public List<Movie> getAll();
  public Optional<Movie> getById(String id);
  public List<String> getNewReleases();
  public Movie save(Movie movie);
}
