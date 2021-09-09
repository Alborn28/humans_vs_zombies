package se.experis.humans_vs_zombies.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Player {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private boolean isHuman;

    @Column(nullable = false)
    private boolean isPatientZero;

    @Column()
    private String biteCode;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;
    @OneToMany(mappedBy = "killer", cascade = CascadeType.ALL)
    private List<Kill> kills = new ArrayList<>();
    @OneToOne(mappedBy = "victim", cascade = CascadeType.ALL)
    private Kill death;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Chat> chats;
    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private SquadMember squadMember;

    @JsonGetter("game")
    public String game() {
        if (game != null) {
            return "/api/v1/game/" + game.getId();
        } else {
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
