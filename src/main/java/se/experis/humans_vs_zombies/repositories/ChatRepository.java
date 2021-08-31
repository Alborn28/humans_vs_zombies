package se.experis.humans_vs_zombies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.experis.humans_vs_zombies.models.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
