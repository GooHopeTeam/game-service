package ru.goohope.gameservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.goohope.gameservice.dto.error.ApiError;
import ru.goohope.gameservice.dto.error.ValidationError;
import ru.goohope.gameservice.dto.response.FullGameDto;
import ru.goohope.gameservice.dto.response.PublisherDto;
import ru.goohope.gameservice.dto.response.ShortGameDto;
import ru.goohope.gameservice.dto.response.TagDto;
import ru.goohope.gameservice.exception.UndefinedSortFieldException;
import ru.goohope.gameservice.service.GameService;

import javax.validation.ConstraintViolationException;
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
    public ResponseEntity<List<ShortGameDto>> getAllGames(
            @RequestParam(name = "page", defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(name = "size", defaultValue = "10") @Positive int size,
            @RequestParam(name = "tags", required = false) List<String> tags,
            @RequestParam(name = "sort", defaultValue = "releaseDate") String sortBy,
            @RequestParam(name = "order", defaultValue = "asc") String sortOrder) {
        return ResponseEntity.ok(gameService.getGames(page, size, tags, sortBy, sortOrder));
    }

    @GetMapping(value = "/games/{id}")
    public ResponseEntity<FullGameDto> getGameById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @GetMapping(value = "/publishers/{id}")
    public ResponseEntity<PublisherDto> getPublisherById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getPublisherById(id));
    }

    @GetMapping(value = "/tags")
    public ResponseEntity<List<TagDto>> getAllTags() {
        return ResponseEntity.ok(gameService.getAllTags());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationError> handleConstraintViolationException(ConstraintViolationException exception) {
        ValidationError validationError = new ValidationError();
        for (var violation : exception.getConstraintViolations()) {
            String queryParamPath = violation.getPropertyPath().toString();
            String queryParam = queryParamPath.contains(".") ?
                    queryParamPath.substring(queryParamPath.indexOf(".") + 1) :
                    queryParamPath;
            String constraintName = violation.getConstraintDescriptor().getAnnotation()
                    .annotationType().getSimpleName();
            validationError.addDetail(queryParam, ValidationError.getConstraintDescription(constraintName));
        }
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleNoSuchElementFoundException(NoSuchElementException exception) {
        record EmptyObject() {}
        return new ResponseEntity<>(new EmptyObject(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UndefinedSortFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleUndefinedSortFieldException(UndefinedSortFieldException exception) {
        return new ResponseEntity<>(new ApiError("undefined_sort_field"), HttpStatus.BAD_REQUEST);
    }

}
