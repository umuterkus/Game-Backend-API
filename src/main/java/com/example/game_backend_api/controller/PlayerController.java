package com.example.game_backend_api.controller;


import com.example.game_backend_api.dto.LeaderboardEntry;
import com.example.game_backend_api.dto.PlayerResponse;
import com.example.game_backend_api.model.Player;
import com.example.game_backend_api.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/players")
    public PlayerResponse createPlayer(@Valid @RequestBody Player request) {
        Player player = playerService.createPlayer(request.getUsername(), request.getEmail(), request.getPassword());
        return new PlayerResponse(player.getId(), player.getUsername(), player.getEmail(), player.getTotalScore());
    }

    @GetMapping("/players/{id}")
    public PlayerResponse getPlayer(@PathVariable Long id)
    {
        Player player = playerService.getPlayerById(id);
        return new PlayerResponse(player.getId(), player.getUsername(), player.getEmail(), player.getTotalScore());

    }

    @GetMapping("/leaderboard")
    public List<LeaderboardEntry> getLeaderboard() {
        return playerService.getLeaderboard();
    }

    @GetMapping("/players")
    public List<PlayerResponse> getPlayers() {
        List<Player> players = playerService.getPlayers();
        List<PlayerResponse> playerResponseList = new ArrayList<>();
        for (Player player : players) {
            playerResponseList.add(new PlayerResponse(player.getId(), player.getUsername(), player.getEmail(), player.getTotalScore()));
        }
        return playerResponseList;

    }

    @PutMapping("/players/{id}")
    public PlayerResponse updatePlayer(@PathVariable Long id, @RequestBody Player request) {
        Player player = playerService.updatePlayer(id, request);
        return  new PlayerResponse(player.getId(), player.getUsername(), player.getEmail(), player.getTotalScore());
    }

    @DeleteMapping("/players/{id}")
    public String deletePlayer(@PathVariable Long id){
        return playerService.deletePlayer(id);
    }

}
