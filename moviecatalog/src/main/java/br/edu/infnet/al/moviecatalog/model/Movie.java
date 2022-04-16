package br.edu.infnet.al.moviecatalog.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Movie {

  public Movie(String id, String name){
    this.id = id;
    this.name = name;
  }

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private String name;
  private String category;
  private String directors;
  private String writers;
  private String actors;
  private String releaseDate;
}
