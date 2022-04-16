package br.edu.infnet.al.ticketmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RefreshScope
public class TicketmanagerApplication {

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
    return restTemplateBuilder.build();
  }

  public static void main(String[] args) {
    SpringApplication.run(TicketmanagerApplication.class, args);
  }

}
