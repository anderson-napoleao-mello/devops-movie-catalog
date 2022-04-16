package br.edu.infnet.al.ticketmanager.service;

import java.util.List;
import java.util.Optional;

public interface ConsumerService {
    public Optional<List<String>> getNewReleases();
}
