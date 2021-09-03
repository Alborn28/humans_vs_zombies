package se.experis.humans_vs_zombies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.experis.humans_vs_zombies.models.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
