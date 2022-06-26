package ru.goohope.gameservice.service;

import ru.goohope.gameservice.dto.request.PublisherDtoRequest;
import ru.goohope.gameservice.dto.response.PublisherDtoResponse;

import java.util.List;

public interface PublisherService {

    PublisherDtoResponse getPublisherById(Long id);
    List<PublisherDtoResponse> getAllProposedPublishers();
    PublisherDtoResponse getProposedPublisherById(Long id);
    void addProposedPublisher(PublisherDtoRequest publisherDto);
    void approvePublisher(Long id);

}
