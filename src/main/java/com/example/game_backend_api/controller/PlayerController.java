package com.example.game_backend_api.controller;


import com.example.game_backend_api.dto.LeaderboardEntry;
import com.example.game_backend_api.model.Player;
import com.example.game_backend_api.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/players")
    public Player createPlayer(@Valid @RequestBody Player request) {
        return playerService.createPlayer(request.getUsername(), request.getEmail());
    }

    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable Long id)
    {
        return playerService.getPlayerById(id);

    }

    @GetMapping("/leaderboard")
    public List<LeaderboardEntry> getLeaderboard() {
        return playerService.getLeaderboard();
    }

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return playerService.getPlayers();
    }

    @PutMapping("/players/{id}")
    public Player updatePlayer(@PathVariable Long id, @RequestBody Player request) {
        return playerService.updatePlayer(id, request);
    }

    @DeleteMapping("/players/{id}")
    public String deletePlayer(@PathVariable Long id){
        return playerService.deletePlayer(id);
    }

}
