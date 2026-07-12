package com.example.game_backend_api.controller;


import com.example.game_backend_api.dto.*;
import com.example.game_backend_api.model.Player;
import com.example.game_backend_api.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/players")
    public PlayerResponse createPlayer(@Valid @RequestBody CreatePlayerRequest request) {
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

    @PutMapping("/players/me")
    public PlayerResponse updatePlayer(Principal principal, @RequestBody UpdatePlayerRequest request) {
        String username = principal.getName();
        Player player = playerService.updatePlayer(username, request);
        return  new PlayerResponse(player.getId(), player.getUsername(), player.getEmail(), player.getTotalScore());
    }

    @DeleteMapping("/players/me")
    public String deletePlayer(Principal principal)
    {
        String username = principal.getName();
        return playerService.deletePlayerByUsername(username);
    }


    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request)
    {
        return playerService.login(request.getUsername(), request.getPassword());

    }

}
