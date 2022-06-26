package ru.goohope.gameservice.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import ru.goohope.gameservice.dto.request.PublisherDtoRequest;
import ru.goohope.gameservice.dto.response.PublisherDtoResponse;
import ru.goohope.gameservice.exception.PublisherAlreadyExistsException;
import ru.goohope.gameservice.model.ProposedPublisher;
import ru.goohope.gameservice.model.Publisher;
import ru.goohope.gameservice.repository.ProposedPublisherRepository;
import ru.goohope.gameservice.repository.PublisherRepository;
import ru.goohope.gameservice.service.PublisherService;

import java.util.List;

@Repository
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final ProposedPublisherRepository proposedPublisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository,
                                ProposedPublisherRepository proposedPublisherRepository) {
        this.publisherRepository = publisherRepository;
        this.proposedPublisherRepository = proposedPublisherRepository;
    }

    @Override
    public PublisherDtoResponse getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow();
        return PublisherDtoResponse.fromModel(publisher);
    }

    @Override
    public List<PublisherDtoResponse> getAllProposedPublishers() {
        return proposedPublisherRepository.findAll()
                .stream().map(PublisherDtoResponse::fromModel).toList();
    }

    @Override
    public PublisherDtoResponse getProposedPublisherById(Long id) {
        ProposedPublisher publisher = proposedPublisherRepository.findById(id).orElseThrow();
        return PublisherDtoResponse.fromModel(publisher);
    }

    @Override
    public void addProposedPublisher(PublisherDtoRequest publisherDto) {
        try {
            publisherRepository.save(publisherDto.toModel());
        } catch (DataIntegrityViolationException exception) {
            if (exception.getCause() instanceof ConstraintViolationException violation) {
                if (violation.getConstraintName().equals("unique_name")) {
                    throw new PublisherAlreadyExistsException();
                }
            }
            throw exception;
        }
    }

    @Override
    public void approvePublisher(Long id) {

    }
}
