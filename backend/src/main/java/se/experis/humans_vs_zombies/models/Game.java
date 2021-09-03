package se.experis.humans_vs_zombies.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import se.experis.humans_vs_zombies.models.enumerators.GameState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL)
    private List<Player> players = new ArrayList<>();

    @JsonGetter("players")
    public List<String> players() {
        return players.stream()
                .map(player -> {
                    return "/api/v1/game/" + this.getId() + "/player/" + player.getId();
                }).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL)
    private List<Kill> kills = new ArrayList<>();

    @JsonGetter("kills")
    public List<String> kills() {
        return kills.stream()
                .map(kill -> {
                    return "/api/v1/game/" + this.getId() + "/kill/" + kill.getId();
                }).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL)
    private List<Squad> squads = new ArrayList<>();

    @JsonGetter("squads")
    public List<String> squads() {
        return squads.stream()
                .map(squad -> {
                    return "/api/v1/game/" + this.getId() + "/squad/" + squad.getId();
                }).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL)
    private List<Mission> missions = new ArrayList<>();

    @JsonGetter("missions")
    public List<String> missions() {
        return missions.stream()
                .map(mission -> {
                    return "/api/v1/game/" + this.getId() + "/mission/" + mission.getId();
                }).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL)
    private List<Chat> chats = new ArrayList<>();

    @JsonGetter("chats")
    public List<String> chats() {
        return chats.stream()
                .map(chat -> {
                    return "/api/v1/game/" + this.getId() + "/chat/" + chat.getId();
                }).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL)
    private List<SquadMember> squadMembers = new ArrayList<>();

    @JsonGetter("squadMembers")
    public List<String> squadMembers() {
        return squadMembers.stream()
                .map(squadMember -> {
                    return "/api/v1/game/" + this.getId() + "/squadMember/" + squadMember.getId();
                }).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL)
    private List<SquadCheckin> squadCheckins = new ArrayList<>();

    @JsonGetter("squadCheckins")
    public List<String> squadCheckins() {
        return squadCheckins.stream()
                .map(squadCheckin -> {
                    return "/api/v1/game/" + this.getId() + "/squadCheckin/" + squadCheckin.getId();
                }).collect(Collectors.toList());
    }

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
