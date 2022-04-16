package br.edu.infnet.al.moviecatalog.repository;

import br.edu.infnet.al.moviecatalog.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
}
