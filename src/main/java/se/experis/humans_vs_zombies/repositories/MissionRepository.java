package se.experis.humans_vs_zombies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.experis.humans_vs_zombies.models.Mission;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> getByGameId(Long gameId);
}
