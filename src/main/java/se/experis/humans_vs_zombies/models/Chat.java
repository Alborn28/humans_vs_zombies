package se.experis.humans_vs_zombies.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Chat {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean isHumanGlobal;

    @Column(nullable = false)
    private boolean isZombieGlobal;

    @Column(nullable = false)
    private boolean isSquadOnly;

    @Column(nullable = false)
    private Date chatTime;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "squadId")
    private Squad squad;

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHumanGlobal() {
        return isHumanGlobal;
    }

    public void setHumanGlobal(boolean humanGlobal) {
        isHumanGlobal = humanGlobal;
    }

    public boolean isZombieGlobal() {
        return isZombieGlobal;
    }

    public void setZombieGlobal(boolean zombieGlobal) {
        isZombieGlobal = zombieGlobal;
    }

    public boolean isSquadOnly() {
        return isSquadOnly;
    }

    public void setSquadOnly(boolean squadOnly) {
        isSquadOnly = squadOnly;
    }

    public Date getChatTime() {
        return chatTime;
    }

    public void setChatTime(Date chatTime) {
        this.chatTime = chatTime;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Squad getSquad() {
        return squad;
    }

    public void setSquad(Squad squad) {
        this.squad = squad;
    }
}
