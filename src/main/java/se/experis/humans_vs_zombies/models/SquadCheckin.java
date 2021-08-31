package se.experis.humans_vs_zombies.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SquadCheckin {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Date startTime;

    @Column(nullable = false)
    private Date endTime;

    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private String lng;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "squadId")
    private Squad squad;

    @OneToOne
    @JoinColumn(name = "squadMemberId")
    private SquadMember squadMember;

    public Long getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
