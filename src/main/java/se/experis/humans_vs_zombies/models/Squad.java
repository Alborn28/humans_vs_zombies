package se.experis.humans_vs_zombies.models;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "squad")
    private List<Chat> chats;

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
