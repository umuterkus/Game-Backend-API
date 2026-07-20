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

    @PostMapping("/login")
    public String login(@Valid @RequestBody DeviceLoginRequest request) {
        return playerService.deviceLogin(request.getDeviceId(), request.getUsername());
    }

    @GetMapping("/players/me")
    public PlayerResponse getCurrentPlayer(Principal principal) {
        String deviceId = principal.getName();
        Player player = playerService.getPlayerByDeviceId(deviceId);
        return new PlayerResponse(player.getId(), player.getUsername(), player.getTotalScore());
    }

    @PutMapping("/players/me")
    public PlayerResponse updatePlayer(Principal principal, @Valid @RequestBody UpdatePlayerRequest request) {
        String deviceId = principal.getName();
        Player player = playerService.updatePlayer(deviceId, request);
        return new PlayerResponse(player.getId(), player.getUsername(), player.getTotalScore());
    }

//    @DeleteMapping("/players/me")
//    public String deletePlayer(Principal principal) {
//        String deviceId = principal.getName();
//        return playerService.deletePlayerByDeviceId(deviceId);
//    }

//    @GetMapping("/players")
//    public List<PlayerResponse> getPlayers() {
//        List<Player> players = playerService.getPlayers();
//        List<PlayerResponse> playerResponseList = new ArrayList<>();
//        for (Player player : players) {
//            playerResponseList.add(new PlayerResponse(player.getId(), player.getUsername(), player.getTotalScore()));
//        }
//        return playerResponseList;
//    }
}