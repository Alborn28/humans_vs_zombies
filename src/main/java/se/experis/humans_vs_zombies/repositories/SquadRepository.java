package se.experis.humans_vs_zombies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.experis.humans_vs_zombies.models.Squad;

import java.util.List;

public interface SquadRepository extends JpaRepository<Squad, Long> {
    List<Squad> getByGameId(Long gameId);
}
