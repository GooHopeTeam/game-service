package ru.goohope.gameservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.goohope.gameservice.model.Game;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(nativeQuery = true,
            value = "SELECT game.* FROM game " +
                    "JOIN games_genres ON game.id = games_tags.game_id " +
                    "JOIN tag ON games_tags.tag_id = tag.id " +
                    "WHERE tag.name IN :tags " +
                    "GROUP BY game.id " +
                    "ORDER BY COUNT(game.id) DESC")
    Page<Game> findGamesByTags(@Param("tags") List<String> tags, Pageable pageable);

}
