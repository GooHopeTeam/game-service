package ru.goohope.gameservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goohope.gameservice.model.ProposedGame;
import ru.goohope.gameservice.model.ProposedPublisher;

public interface ProposedPublisherRepository extends JpaRepository<ProposedPublisher, Long> {
}
