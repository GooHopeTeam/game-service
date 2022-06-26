package ru.goohope.gameservice.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "publisher", uniqueConstraints = @UniqueConstraint(name = "unique_name", columnNames = {"name"}))
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    Long id;

    @Column(name = "name", unique = true, nullable = false, length = 512)
    String name;

    @Column(name = "description", nullable = false, columnDefinition = "text")
    String description;

    @Column(name = "logo_url", nullable = false)
    String logoUrl;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    List<Game> games;

    public Publisher() {

    }

    public Publisher(String name, String description, String logoUrl) {
        this.name = name;
        this.description = description;
        this.logoUrl = logoUrl;
    }

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
