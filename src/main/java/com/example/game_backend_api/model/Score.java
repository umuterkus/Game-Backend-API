package com.example.game_backend_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int points;
    private String gameMode;
    private LocalDateTime playedAt;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public Score() {
    }

    public Score(int points, String gameMode, Player player) {
        this.points = points;
        this.gameMode = gameMode;
        this.player = player;
        this.playedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public String getGameMode() { return gameMode; }
    public void setGameMode(String gameMode) { this.gameMode = gameMode; }

    public LocalDateTime getPlayedAt() { return playedAt; }
    public void setPlayedAt(LocalDateTime playedAt) { this.playedAt = playedAt; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

}