package com.example.game_backend_api.model;

import jakarta.persistence.*;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private int totalScore;


    @OneToMany(mappedBy = "player")
    private List<Score> scoreList = new ArrayList<>();

    public Player() {
    }

    public Player(String username, String email) {
        this.username = username;
        this.email = email;
        this.totalScore = 0;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getTotalScore() { return totalScore; }
    public void setTotalScore(int totalScore) { this.totalScore = totalScore; }

}