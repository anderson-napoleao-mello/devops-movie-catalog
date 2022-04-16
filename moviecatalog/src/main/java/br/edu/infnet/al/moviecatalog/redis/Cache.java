package br.edu.infnet.al.moviecatalog.redis;

import br.edu.infnet.al.moviecatalog.model.Movie;

import java.util.List;

public interface Cache {
  void insert(Movie movie);
  List<String> getNewReleases();
}
