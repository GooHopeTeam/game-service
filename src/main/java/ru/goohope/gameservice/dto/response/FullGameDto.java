package ru.goohope.gameservice.dto.response;

import ru.goohope.gameservice.model.Game;
import ru.goohope.gameservice.model.Publisher;
import ru.goohope.gameservice.model.Screenshot;
import ru.goohope.gameservice.model.Tag;

import java.util.Date;
import java.util.List;

public record FullGameDto(Long id,
                          String name,
                          String shortDescription,
                          String fullDescription,
                          Long publisherId,
                          String publisherName,
                          String imageUrl,
                          Date releaseDate,
                          List<String> screenshots,
                          List<String> tags) {

    public static FullGameDto fromModel(Game game) {
        Publisher publisher = game.getPublisher();
        List<String> screenshots = game.getScreenshots().stream().map(Screenshot::getUrl).toList();
        List<String> tags = game.getTags().stream().map(Tag::getName).toList();
        return new FullGameDto(game.getId(), game.getName(), game.getShortDescription(), game.getFullDescription(),
                publisher.getId(), publisher.getName(), game.getImageUrl(), game.getReleaseDate(), screenshots, tags);
    }

}
