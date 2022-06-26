package ru.goohope.gameservice.dto.response;

import ru.goohope.gameservice.model.Game;
import ru.goohope.gameservice.model.Tag;

import java.util.List;

public record ShortGameDtoResponse(Long id,
                                   String name,
                                   String shortDescription,
                                   String imageUrl,
                                   List<String> tags) {

    public static ShortGameDtoResponse fromModel(Game game) {
        List<String> tags = game.getTags().stream().map(Tag::getName).toList();
        return new ShortGameDtoResponse(game.getId(), game.getName(), game.getShortDescription(),
                game.getImageUrl(), tags);
    }
}
