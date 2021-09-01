package se.experis.humans_vs_zombies.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Squad {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isHuman;

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

    @OneToMany(mappedBy = "squad")
    private List<Chat> chats;

    @JsonGetter("chats")
    public List<String> chats() {
        return chats.stream()
                .map(chat -> {
                    return "/api/v1/game/" + game.getId() + "/chat/" + chat.getId();
                }).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "squad")
    private List<SquadMember> squadMembers;

    @OneToMany(mappedBy = "squad")
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

    public boolean isHuman() {
        return isHuman;
    }

    public void setHuman(boolean human) {
        isHuman = human;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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
