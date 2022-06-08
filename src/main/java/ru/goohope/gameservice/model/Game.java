package ru.goohope.gameservice.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name", unique = true, nullable = false)
    String name;

    @Column(name = "short_description", length = 1024, nullable = false)
    String shortDescription;

    @Column(name = "full_description", nullable = false, columnDefinition = "TEXT")
    String fullDescription;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    Publisher publisher;

    @Column(name = "image_url", nullable = false, length = 1024)
    String imageUrl;

    @Column(name = "release_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date releaseDate;

    @ManyToMany
    @JoinTable(name = "games_tags",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    List<Tag> tags;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    List<Screenshot> screenshots;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Screenshot> getScreenshots() {
        return screenshots;
    }
}
