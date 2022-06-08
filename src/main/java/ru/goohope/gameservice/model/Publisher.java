package ru.goohope.gameservice.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(name = "name", unique = true, nullable = false, length = 512)
    String name;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
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
