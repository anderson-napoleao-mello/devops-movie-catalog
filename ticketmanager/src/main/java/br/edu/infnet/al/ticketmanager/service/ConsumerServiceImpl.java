package br.edu.infnet.al.ticketmanager.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService{

    private final RestTemplate client;

    @Override
    public Optional<List<String>> getNewReleases() {
        log.info("request for movie-service new releases");
        List<String> result = Arrays.asList(Objects.requireNonNull(client.getForObject("http://movie-service:8080/movie/newreleases", String[].class)));
        if(result.isEmpty()) return Optional.empty();
        else return Optional.of(result);
    }
}
