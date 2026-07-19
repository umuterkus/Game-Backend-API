package com.example.game_backend_api.controller;

import com.example.game_backend_api.dto.CreateScoreRequest;
import com.example.game_backend_api.dto.LeaderboardEntry;
import com.example.game_backend_api.model.Player;
import com.example.game_backend_api.model.Score;
import com.example.game_backend_api.service.PlayerService;
import com.example.game_backend_api.service.ScoreService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public Score createScore(@Valid @RequestBody CreateScoreRequest request, Principal principal) {
        return scoreService.addScore(request.getScore(), request.getGameMode(), principal.getName());
    }

    @GetMapping("/players/{id}/scores")
    public List<Score> getScoresById(@PathVariable Long id){
        return scoreService.getScoresByPlayerId(id);

    }

    @GetMapping("/leaderboard")
    public List<LeaderboardEntry> getLeaderboard(
            @RequestParam(value = "filter", defaultValue = "all") String filter,
            @RequestParam(value = "limit", defaultValue = "2") int limit)
    {
        return scoreService.getLeaderboard(filter, limit);
    }

}
