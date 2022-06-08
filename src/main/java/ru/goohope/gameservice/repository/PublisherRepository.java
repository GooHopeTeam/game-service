package ru.goohope.gameservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goohope.gameservice.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
