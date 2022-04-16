package br.edu.infnet.al.moviecatalog.service.impl;

import br.edu.infnet.al.moviecatalog.model.Movie;
import br.edu.infnet.al.moviecatalog.redis.RedisCache;
import br.edu.infnet.al.moviecatalog.repository.MovieRepository;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;

@ExtendWith(MockitoExtension.class)
public class MovieSeviceTest {

    private static final UUID id1 = UUID.randomUUID();
    private static final UUID id2 = UUID.randomUUID();

    @InjectMocks
    MovieServiceImpl service;

    @Mock
    MovieRepository repository;

    @Mock
    RedisCache redisCache;

    private List<Movie> movies;

    @BeforeEach
    private void setUp(){
        movies = Arrays.asList(
                new Movie(id1.toString(),"Movie1"),
                new Movie(id2.toString(),"Movie2")
        );
        Mockito.lenient().when(repository.findAll()).thenReturn(movies);
        Mockito.lenient().when(repository.findById(id1.toString())).thenReturn(Optional.of(movies.get(0)));
        Mockito.lenient().when(redisCache.getNewReleases()).thenReturn(List.of("New Movie"));
    }

    @Test
    @DisplayName("Test get all movies")
    public void testGetAll(){
        List<Movie> result = service.getAll();
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("Test get by id movies")
    public void testGetById(){
        Optional<Movie> result = service.getById(id1.toString());
        assertFalse(result.isEmpty());
        assertEquals("Movie1", result.get().getName());
        assertEquals(id1.toString(), result.get().getId().toString());
    }

    @Test
    @DisplayName("Test get new releases movies")
    public void testGetNewReleases(){
        List<String> result = redisCache.getNewReleases();
        assertFalse(result.isEmpty());
        assertEquals("New Movie", result.get(0));
    }

}
