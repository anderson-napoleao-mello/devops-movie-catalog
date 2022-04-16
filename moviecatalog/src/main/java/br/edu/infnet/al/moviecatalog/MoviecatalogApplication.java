package br.edu.infnet.al.moviecatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class MoviecatalogApplication {

  public static void main(String[] args) {
    SpringApplication.run(MoviecatalogApplication.class, args);
  }

}
