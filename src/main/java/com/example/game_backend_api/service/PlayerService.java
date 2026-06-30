package com.example.game_backend_api.service;

import com.example.game_backend_api.dto.LeaderboardEntry;
import com.example.game_backend_api.model.Player;
import com.example.game_backend_api.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<LeaderboardEntry> getLeaderboard() {
        List<Player> players = playerRepository.findAllByOrderByTotalScoreDesc();

        List<LeaderboardEntry> leaderboard = new ArrayList<>();
        for (Player player : players) {
            leaderboard.add(new LeaderboardEntry(player.getUsername(), player.getTotalScore()));
        }
        return leaderboard;
    }

    public List<Player> getPlayers() {
        List<Player> players = playerRepository.findAll();
        return players;
    }

    public Player updatePlayer(Long id, Player request)
    {
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Oyuncu bulunamadı! ID: " + id));

        existingPlayer.setUsername(request.getUsername());
        existingPlayer.setEmail(request.getEmail());
        return playerRepository.save(existingPlayer);
    }

    public String deletePlayer(Long id)
    {
        if(playerRepository.existsById(id)){
            playerRepository.deleteById(id);
            return "Player with id: " + id + " deleted!";
        }
        else {
            throw new IllegalArgumentException("Cannot find player with id: " + id);
        }
    }
}
