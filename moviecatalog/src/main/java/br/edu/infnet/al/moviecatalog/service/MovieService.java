package br.edu.infnet.al.moviecatalog.service;

import br.edu.infnet.al.moviecatalog.model.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieService {
  List<Movie> getAll();
  Optional<Movie> getById(String id);
  List<String> getNewReleases();
  Movie save(Movie movie);
}
