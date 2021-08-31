package se.experis.humans_vs_zombies.models;

import se.experis.humans_vs_zombies.models.enumerators.GameState;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GameState gameState;

    @Column
    private String nwLat;

    @Column
    private String nwLng;

    @Column
    private String seLat;

    @Column
    private String seLng;

    @OneToMany(mappedBy = "game")
    private List<Player> players;

    @OneToMany(mappedBy = "game")
    private List<Kill> kills;

    @OneToMany(mappedBy = "game")
    private List<Squad> squads;

    @OneToMany(mappedBy = "game")
    private List<Mission> missions;

    @OneToMany(mappedBy = "game")
    private List<Chat> chats;

    @OneToMany(mappedBy = "game")
    private List<SquadMember> squadMembers;

    @OneToMany(mappedBy = "game")
    private List<SquadCheckin> squadCheckins;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public String getNwLat() {
        return nwLat;
    }

    public void setNwLat(String nwLat) {
        this.nwLat = nwLat;
    }

    public String getNwLng() {
        return nwLng;
    }

    public void setNwLng(String nwLng) {
        this.nwLng = nwLng;
    }

    public String getSeLat() {
        return seLat;
    }

    public void setSeLat(String seLat) {
        this.seLat = seLat;
    }

    public String getSeLng() {
        return seLng;
    }

    public void setSeLng(String seLng) {
        this.seLng = seLng;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Kill> getKills() {
        return kills;
    }

    public void setKills(List<Kill> kills) {
        this.kills = kills;
    }

    public List<Squad> getSquads() {
        return squads;
    }

    public void setSquads(List<Squad> squads) {
        this.squads = squads;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public List<SquadMember> getSquadMembers() {
        return squadMembers;
    }

    public void setSquadMembers(List<SquadMember> squadMembers) {
        this.squadMembers = squadMembers;
    }

    public List<SquadCheckin> getSquadCheckins() {
        return squadCheckins;
    }

    public void setSquadCheckins(List<SquadCheckin> squadCheckins) {
        this.squadCheckins = squadCheckins;
    }
}
