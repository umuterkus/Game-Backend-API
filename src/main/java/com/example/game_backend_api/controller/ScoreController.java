package com.example.game_backend_api.controller;

import com.example.game_backend_api.dto.LeaderboardEntry;
import com.example.game_backend_api.model.Player;
import com.example.game_backend_api.model.Score;
import com.example.game_backend_api.service.PlayerService;
import com.example.game_backend_api.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreController {

    private final ScoreService scoreService;
    private final PlayerService playerService;

    public ScoreController(ScoreService scoreService, PlayerService playerService) {
        this.scoreService = scoreService;
        this.playerService = playerService;
    }


    @PostMapping("/scores")
    public Score createScore(@RequestParam int score, @RequestParam String gameMode, @RequestParam Long playerId)
    {
        return scoreService.addScore(score, gameMode, playerService.getPlayerById(playerId));

    }

    @GetMapping("/players/{id}/scores")
    public List<Score> getScoresById(@PathVariable Long id){
        return scoreService.getScoresByPlayerId(id);

    }

    @GetMapping("/leaderboard")
    public List<LeaderboardEntry> getLeaderboard() {
        return scoreService.getLeaderboard();
    }

}
