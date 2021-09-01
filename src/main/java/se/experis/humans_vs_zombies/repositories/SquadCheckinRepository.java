package se.experis.humans_vs_zombies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.experis.humans_vs_zombies.models.SquadCheckin;

import java.util.List;

public interface SquadCheckinRepository extends JpaRepository<SquadCheckin, Long> {
    List<SquadCheckin> getByGameIdAndSquadId(Long gameId, Long squadId);
}
