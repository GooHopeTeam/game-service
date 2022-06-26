package ru.goohope.gameservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.goohope.gameservice.dto.request.PublisherDtoRequest;
import ru.goohope.gameservice.dto.response.PublisherDtoResponse;
import ru.goohope.gameservice.service.PublisherService;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@Validated
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping(value = "/publishers/{id}")
    public PublisherDtoResponse getPublisherById(@PathVariable Long id) {
        return publisherService.getPublisherById(id);
    }

    @PostMapping("/proposed_publishers")
    public void proposePublisher(@Valid @RequestBody PublisherDtoRequest publisherDto) {
        publisherService.addProposedPublisher(publisherDto);
    }

    @GetMapping("/proposed_publishers/")
    public List<PublisherDtoResponse> getAllProposedPublisher() {
        return publisherService.getAllProposedPublishers();
    }

    @GetMapping("/proposed_publishers/{id}")
    public PublisherDtoResponse getProposedPublisherById(@PathVariable(name = "id") Long id) {
        return publisherService.getProposedPublisherById(id);
    }

    @PatchMapping("/proposed_publishers/{id}")
    public void approvePublisher(@PathVariable(name = "id") Long id) {
        publisherService.approvePublisher(id);
    }

}
