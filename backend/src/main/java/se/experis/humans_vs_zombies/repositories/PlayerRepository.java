package se.experis.humans_vs_zombies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.experis.humans_vs_zombies.models.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> getByGameId(Long gameId);
    Player getByEmailAndGameId(String email, Long gameId);
    Boolean existsByEmailAndGameId(String email, Long gameId);
}
