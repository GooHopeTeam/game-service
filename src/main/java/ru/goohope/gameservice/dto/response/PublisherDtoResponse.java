package ru.goohope.gameservice.dto.response;

import ru.goohope.gameservice.model.Publisher;

import java.util.List;

public record PublisherDtoResponse(Long id,
                                   String name,
                                   List<ShortGameDtoResponse> games) {

    public static PublisherDtoResponse fromModel(Publisher publisher) {
        return new PublisherDtoResponse(publisher.getId(), publisher.getName(),
                publisher.getGames().stream().map(ShortGameDtoResponse::fromModel).toList());
    }

}
