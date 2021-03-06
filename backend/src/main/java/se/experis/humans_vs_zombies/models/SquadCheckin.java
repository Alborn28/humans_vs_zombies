package se.experis.humans_vs_zombies.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SquadCheckin {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Date timeOfCheckIn;

    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private String lng;

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

    @ManyToOne
    @JoinColumn(name = "squadId")
    private Squad squad;

    @JsonGetter("squad")
    public String squad() {
        if(squad != null && game != null){
            return "/api/v1/game/" + game.getId() + "/squad/" + squad.getId();
        }else{
            return null;
        }
    }

    @OneToOne
    @JoinColumn(name = "squadMemberId")
    private SquadMember squadMember;

    @JsonGetter("squadMember")
    public String squadMember() {
        if (squadMember != null && squadMember.getPlayer() != null) {
            return "/api/v1/game/" + game.getId() + "/player/" + squadMember.getPlayer().getId();
        } else {
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public Date getTimeOfCheckIn() {
        return timeOfCheckIn;
    }

    public void setTimeOfCheckIn(Date timeOfCheckIn) {
        this.timeOfCheckIn = timeOfCheckIn;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
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

    public SquadMember getSquadMember() {
        return squadMember;
    }

    public void setSquadMember(SquadMember squadMember) {
        this.squadMember = squadMember;
    }
}
