package ru.goohope.gameservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goohope.gameservice.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
