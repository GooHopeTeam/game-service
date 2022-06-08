package ru.goohope.gameservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import ru.goohope.gameservice.dto.response.FullGameDto;
import ru.goohope.gameservice.dto.response.PublisherDto;
import ru.goohope.gameservice.dto.response.ShortGameDto;
import ru.goohope.gameservice.dto.response.TagDto;
import ru.goohope.gameservice.exception.UndefinedSortFieldException;
import ru.goohope.gameservice.model.Game;
import ru.goohope.gameservice.model.Publisher;
import ru.goohope.gameservice.repository.GameRepository;
import ru.goohope.gameservice.repository.PublisherRepository;
import ru.goohope.gameservice.repository.TagRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final TagRepository tagRepository;
    private final PublisherRepository publisherRepository;

    private final Set<String> allowedSortFields = Set.of("id", "name", "release_date");

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
    public List<ShortGameDto> getGames(int page, int size, List<String> tags, String sortField, String sortOrder) {
        Pageable pageable = makePageRequest(page, size, sortField, sortOrder);
        if (tags == null) {
            return gameRepository.findAll(pageable).map(ShortGameDto::fromModel).toList();
        }
        return gameRepository.findGamesByTags(tags, pageable).map(ShortGameDto::fromModel).toList();
    }

    @Override
    public FullGameDto getGameById(Long id) {
        Game game = gameRepository.findById(id).orElseThrow();
        return FullGameDto.fromModel(game);
    }

    @Override
    public PublisherDto getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow();
        return PublisherDto.fromModel(publisher);
    }

    @Override
    public List<TagDto> getAllTags() {
        return tagRepository.findAll().stream().map(TagDto::fromModel).toList();
    }
}
