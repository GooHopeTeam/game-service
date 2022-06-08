package ru.goohope.gameservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name", unique = true, nullable = false, length = 128)
    String name;

    @ManyToMany(mappedBy = "tags")
    List<Game> games;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Game> getGames() {
        return games;
    }
}
