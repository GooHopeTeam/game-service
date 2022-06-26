package ru.goohope.gameservice.service;

import ru.goohope.gameservice.dto.response.FullGameDtoResponse;
import ru.goohope.gameservice.dto.response.PublisherDtoResponse;
import ru.goohope.gameservice.dto.response.ShortGameDtoResponse;
import ru.goohope.gameservice.dto.response.TagDtoResponse;

import java.util.List;

public interface GameService {

    List<ShortGameDtoResponse> getGames(int page, int size, List<String> tags, String sortBy, String sortOrder);
    FullGameDtoResponse getGameById(Long id);
    List<TagDtoResponse> getAllTags();

}
