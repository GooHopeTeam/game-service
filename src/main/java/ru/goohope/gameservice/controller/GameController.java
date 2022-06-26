package ru.goohope.gameservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.goohope.gameservice.dto.response.FullGameDtoResponse;
import ru.goohope.gameservice.dto.response.PublisherDtoResponse;
import ru.goohope.gameservice.dto.response.ShortGameDtoResponse;
import ru.goohope.gameservice.dto.response.TagDtoResponse;
import ru.goohope.gameservice.service.GameService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@Validated
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "/games/")
    public ResponseEntity<List<ShortGameDtoResponse>> getAllGames(
            @RequestParam(name = "page", defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(name = "size", defaultValue = "10") @Positive int size,
            @RequestParam(name = "tags", required = false) List<String> tags,
            @RequestParam(name = "sort", defaultValue = "releaseDate") String sortBy,
            @RequestParam(name = "order", defaultValue = "asc") String sortOrder) {
        return ResponseEntity.ok(gameService.getGames(page, size, tags, sortBy, sortOrder));
    }

    @GetMapping(value = "/games/{id}")
    public ResponseEntity<FullGameDtoResponse> getGameById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @GetMapping(value = "/tags")
    public ResponseEntity<List<TagDtoResponse>> getAllTags() {
        return ResponseEntity.ok(gameService.getAllTags());
    }

}
