package se.experis.humans_vs_zombies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.experis.humans_vs_zombies.models.Kill;

import java.util.List;

public interface KillRepository extends JpaRepository<Kill, Long> {
    List<Kill> getByGameId(Long gameId);
}
