package se.experis.humans_vs_zombies.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Mission {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isHumanVisible;

    @Column(nullable = false)
    private boolean isZombieVisible;

    @Column
    private String description;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHumanVisible() {
        return isHumanVisible;
    }

    public void setHumanVisible(boolean humanVisible) {
        isHumanVisible = humanVisible;
    }

    public boolean isZombieVisible() {
        return isZombieVisible;
    }

    public void setZombieVisible(boolean zombieVisible) {
        isZombieVisible = zombieVisible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
