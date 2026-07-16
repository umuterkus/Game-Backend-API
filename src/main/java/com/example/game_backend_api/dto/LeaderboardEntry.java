package com.example.game_backend_api.dto;

public class LeaderboardEntry {

    private String username;
    private int totalScore;
    private int rank;


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

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

}