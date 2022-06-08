package ru.goohope.gameservice.dto.response;

import com.fasterxml.jackson.annotation.JsonRawValue;
import ru.goohope.gameservice.model.Game;
import ru.goohope.gameservice.model.Tag;

import java.util.List;

public record TagDto(String name) {

    public static TagDto fromModel(Tag tag) {
        return new TagDto(tag.getName());
    }
}
