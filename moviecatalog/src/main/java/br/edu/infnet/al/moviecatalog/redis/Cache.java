package br.edu.infnet.al.moviecatalog.redis;

import br.edu.infnet.al.moviecatalog.model.Movie;

import java.util.List;

public interface Cache {
  public void insert(Movie movie);
  public List<String> getNewReleases();
}
