package se.experis.humans_vs_zombies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.experis.humans_vs_zombies.models.Chat;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> getByGameId(Long gameId);
    List<Chat> getByGameIdAndSquadId(Long gameId, Long squadId);
}
