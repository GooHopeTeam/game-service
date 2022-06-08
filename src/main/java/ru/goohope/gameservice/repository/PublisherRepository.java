package ru.goohope.gameservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goohope.gameservice.model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
