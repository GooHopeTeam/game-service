package ru.goohope.gameservice.dto.response;

import ru.goohope.gameservice.model.Tag;

public record TagDtoResponse(String name) {

    public static TagDtoResponse fromModel(Tag tag) {
        return new TagDtoResponse(tag.getName());
    }
}
