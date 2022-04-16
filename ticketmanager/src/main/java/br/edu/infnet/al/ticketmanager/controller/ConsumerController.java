package br.edu.infnet.al.ticketmanager.controller;

import br.edu.infnet.al.ticketmanager.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.SplittableRandom;

@AllArgsConstructor
@RestController
@RequestMapping("newreleases")
public class ConsumerController {

    private final ConsumerService service;

    @GetMapping
    public ResponseEntity<List<String>> getNewReliases(){
        SplittableRandom random = new SplittableRandom();
        int i = random.nextInt(1000);
        if(i>500){
            throw new RuntimeException("Ticket Manager Random Runtime Error");
        }
        return service.getNewReleases().map(ResponseEntity.ok()::body).orElseGet(ResponseEntity.noContent()::build);
    }
}
