package ru.goohope.gameservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.goohope.gameservice.dto.response.FullGameDtoResponse;
import ru.goohope.gameservice.dto.response.PublisherDtoResponse;
import ru.goohope.gameservice.dto.response.ShortGameDtoResponse;
import ru.goohope.gameservice.dto.response.TagDtoResponse;
import ru.goohope.gameservice.exception.UndefinedSortFieldException;
import ru.goohope.gameservice.model.Game;
import ru.goohope.gameservice.model.Publisher;
import ru.goohope.gameservice.repository.GameRepository;
import ru.goohope.gameservice.repository.PublisherRepository;
import ru.goohope.gameservice.repository.TagRepository;
import ru.goohope.gameservice.service.GameService;

import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final TagRepository tagRepository;
    private final PublisherRepository publisherRepository;

    private final Set<String> allowedSortFields = Set.of("id", "name", "releaseDate");

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, TagRepository tagRepository, PublisherRepository publisherRepository) {
        this.gameRepository = gameRepository;
        this.tagRepository = tagRepository;
        this.publisherRepository = publisherRepository;
    }

    private Pageable makePageRequest(int page, int size, String sortField, String sortOrder) {
        if (!allowedSortFields.contains(sortField)) {
            throw new UndefinedSortFieldException();
        }
        return PageRequest.of(page, size,
                Sort.by(sortOrder.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField));
    }

    @Override
    public List<ShortGameDtoResponse> getGames(int page, int size, List<String> tags, String sortField, String sortOrder) {
        Pageable pageable = makePageRequest(page, size, sortField, sortOrder);
        if (tags == null) {
            return gameRepository.findAll(pageable).map(ShortGameDtoResponse::fromModel).toList();
        }
        return gameRepository.findGamesByTags(tags, pageable).map(ShortGameDtoResponse::fromModel).toList();
    }

    @Override
    public FullGameDtoResponse getGameById(Long id) {
        Game game = gameRepository.findById(id).orElseThrow();
        return FullGameDtoResponse.fromModel(game);
    }

    @Override
    public List<TagDtoResponse> getAllTags() {
        return tagRepository.findAll().stream().map(TagDtoResponse::fromModel).toList();
    }
}
