package ru.goohope.gameservice.dto.request;

import ru.goohope.gameservice.model.Publisher;

import javax.validation.constraints.NotBlank;

public record PublisherDtoRequest(
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank String logoUrl) {

    public Publisher toModel() {
        return new Publisher(name, description, logoUrl);
    }

}
