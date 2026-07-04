package com.example.game_backend_api.dto;

public class PlayerResponse {

    private Long id;
    private String username;
    private String email;
    private int totalScore;

    public PlayerResponse(Long id, String username, String email, int totalScore) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.totalScore = totalScore;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getTotalScore() {
        return totalScore;
    }
}