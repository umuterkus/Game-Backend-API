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
        { throw new IllegalArgumentException("Bu email zaten kullanılıyor"); }

        if(playerRepository.existsByUsername(name))
        { throw new IllegalArgumentException("Bu username zaten kullanılıyor"); }

        Player player = new Player(name, email);
        return playerRepository.save(player);
    }
}
