package ru.goohope.gameservice.model;

import javax.persistence.*;

@Entity
@Table(name = "screenshot")
public class Screenshot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "url", nullable = false, length = 1024)
    String url;

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id")
    Game game;

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Game getGame() {
        return game;
    }
}
