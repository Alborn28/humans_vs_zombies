package se.experis.humans_vs_zombies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.experis.humans_vs_zombies.models.Player;
import se.experis.humans_vs_zombies.models.SquadMember;

public interface SquadMemberRepository extends JpaRepository<SquadMember, Long> {
    boolean existsByPlayer(Player player);
    SquadMember deleteByPlayer(Player player);
}
