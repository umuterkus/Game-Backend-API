package com.example.game_backend_api.dto;

public class PlayerResponse {

    private Long id;
    private String username;
    private int totalScore;

    public PlayerResponse(Long id, String username, int totalScore) {
        this.id = id;
        this.username = username;
        this.totalScore = totalScore;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }


    public int getTotalScore() {
        return totalScore;
    }
}