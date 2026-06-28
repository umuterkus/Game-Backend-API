package com.example.game_backend_api.service;

import com.example.game_backend_api.model.Player;
import com.example.game_backend_api.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;

    }

    public Player createPlayer(String name, String email)
    {
        if(playerRepository.existsByEmail(email))
        { throw new IllegalArgumentException("This email already exists!"); }

        if(playerRepository.existsByUsername(name))
        { throw new IllegalArgumentException("This username already exists!"); }

        Player player = new Player(name, email);
        return playerRepository.save(player);
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find player with id: " + id));
    }
}
