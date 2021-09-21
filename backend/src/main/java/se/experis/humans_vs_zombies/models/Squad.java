package se.experis.humans_vs_zombies.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Squad {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 64, nullable = false)
    private String name;

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

    @OneToMany(mappedBy = "squad", cascade = CascadeType.ALL)
    private List<SquadMember> squadMembers;

    @OneToMany(mappedBy = "squad", cascade = CascadeType.ALL)
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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
