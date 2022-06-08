package ru.goohope.gameservice.service;

import ru.goohope.gameservice.dto.response.FullGameDto;
import ru.goohope.gameservice.dto.response.PublisherDto;
import ru.goohope.gameservice.dto.response.ShortGameDto;
import ru.goohope.gameservice.dto.response.TagDto;

import java.util.List;
import java.util.Map;

public interface GameService {

    List<ShortGameDto> getGames(int page, int size, List<String> tags, String sortBy, String sortOrder);
    FullGameDto getGameById(Long id);
    PublisherDto getPublisherById(Long id);
    List<TagDto> getAllTags();

}
