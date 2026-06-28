package com.example.game_backend_api.controller;


import com.example.game_backend_api.model.Player;
import com.example.game_backend_api.service.PlayerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/players")
    public Player createPlayer(@RequestBody Player request) {
        return playerService.createPlayer(request.getUsername(), request.getEmail());
    }

}
