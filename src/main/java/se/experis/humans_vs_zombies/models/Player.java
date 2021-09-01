package se.experis.humans_vs_zombies.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private boolean isHuman;

    @Column(nullable = false)
    private boolean isPatientZero;

    @Column(nullable = false)
    private String biteCode;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @JsonGetter("game")
    public String game() {
        if(game != null){
            return "/api/v1/game/" + game.getId();
        }else{
            return null;
        }
    }

    @OneToMany(mappedBy = "killer", cascade=CascadeType.ALL)
    private List<Kill> kills;

    @OneToOne(mappedBy = "victim", cascade=CascadeType.ALL)
    private Kill death;

    @OneToMany(mappedBy = "player", cascade=CascadeType.ALL)
    private List<Chat> chats;

    @OneToOne(mappedBy = "player", cascade=CascadeType.ALL)
    private SquadMember squadMember;

    public Long getId() {
        return id;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public void setHuman(boolean human) {
        isHuman = human;
    }

    public boolean isPatientZero() {
        return isPatientZero;
    }

    public void setPatientZero(boolean patientZero) {
        isPatientZero = patientZero;
    }

    public String getBiteCode() {
        return biteCode;
    }

    public void setBiteCode(String biteCode) {
        this.biteCode = biteCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Kill> getKills() {
        return kills;
    }

    public void setKills(List<Kill> kills) {
        this.kills = kills;
    }

    public Kill getDeath() {
        return death;
    }

    public void setDeath(Kill death) {
        this.death = death;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public SquadMember getSquadMember() {
        return squadMember;
    }

    public void setSquadMember(SquadMember squadMember) {
        this.squadMember = squadMember;
    }
}
