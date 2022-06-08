package ru.goohope.gameservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goohope.gameservice.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
