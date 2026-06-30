package com.example.game_backend_api.dto;

public class LeaderboardEntry {

    private String username;
    private int totalScore;

    public LeaderboardEntry(String username, int totalScore) {
        this.username = username;
        this.totalScore = totalScore;
    }

    public String getUsername() {
        return username;
    }

    public int getTotalScore() {
        return totalScore;
    }
}