package se.experis.humans_vs_zombies.models;

import se.experis.humans_vs_zombies.models.enumerators.Rank;

import javax.persistence.*;

@Entity
public class SquadMember {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rank rank;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "squadId")
    private Squad squad;

    @OneToOne
    @JoinColumn(name = "playerId")
    private Player player;

    @OneToOne(mappedBy = "squadMember")
    private SquadCheckin squadCheckin;

    public Long getId() {
        return id;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Squad getSquad() {
        return squad;
    }

    public void setSquad(Squad squad) {
        this.squad = squad;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public SquadCheckin getSquadCheckin() {
        return squadCheckin;
    }

    public void setSquadCheckin(SquadCheckin squadCheckin) {
        this.squadCheckin = squadCheckin;
    }
}
