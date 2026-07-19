package com.example.game_backend_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateScoreRequest {

    @Min(value = 0, message = "Score cannot be negative")
    private int score;

    @NotBlank(message = "Game mode cannot be empty")
    private String gameMode;

    public CreateScoreRequest() {
    }

    public CreateScoreRequest(int score, String gameMode) {
        this.score = score;
        this.gameMode = gameMode;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
}
