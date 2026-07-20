package com.example.game_backend_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String deviceId;

    @NotBlank(message = "Username cannot be empty")
    private String username;

    private int totalScore;


    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE)
    private List<Score> scoreList = new ArrayList<>();


    public Player() {
    }

    public Player(String deviceId, String username) {
        this.deviceId = deviceId;
        this.username = username;
        this.totalScore = 0;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getTotalScore() { return totalScore; }
    public void setTotalScore(int totalScore) { this.totalScore = totalScore; }

    public List<Score> getScoreList() { return scoreList; }
    public void setScoreList(List<Score> scoreList) { this.scoreList = scoreList; }

}