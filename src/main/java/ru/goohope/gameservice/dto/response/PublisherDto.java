package ru.goohope.gameservice.dto.response;

import ru.goohope.gameservice.model.Publisher;

import java.util.List;

public record PublisherDto(Long id,
                           String name,
                           List<ShortGameDto> games) {

    public static PublisherDto fromModel(Publisher publisher) {
        return new PublisherDto(publisher.getId(), publisher.getName(),
                publisher.getGames().stream().map(ShortGameDto::fromModel).toList());
    }

}
