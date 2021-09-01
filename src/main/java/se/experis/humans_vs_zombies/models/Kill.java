package se.experis.humans_vs_zombies.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Kill {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Date timeOfDeath;

    @Column
    private String story;

    @Column
    private String lat;

    @Column
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
    @JoinColumn(name = "killerId")
    private Player killer;

    @JsonGetter("killer")
    public String killer() {
        if(killer != null){
            return "/api/v1/game/"+game.getId()+"/player/" + killer.getId();
        }else{
            return null;
        }
    }

    @OneToOne
    @JoinColumn(name = "victimId")
    private Player victim;

    @JsonGetter("victim")
    public String victim() {
        if(victim != null){
            return "/api/v1/game/"+game.getId()+"/player/" + victim.getId();
        }else{
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public Date getTimeOfDeath() {
        return timeOfDeath;
    }

    public void setTimeOfDeath(Date timeOfDeath) {
        this.timeOfDeath = timeOfDeath;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
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

    public Player getKiller() {
        return killer;
    }

    public void setKiller(Player killer) {
        this.killer = killer;
    }

    public Player getVictim() {
        return victim;
    }

    public void setVictim(Player victim) {
        this.victim = victim;
    }
}
