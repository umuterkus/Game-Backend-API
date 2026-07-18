package com.example.game_backend_api.service;

import com.example.game_backend_api.config.JwtUtil;
import com.example.game_backend_api.dto.LeaderboardEntry;
import com.example.game_backend_api.dto.UpdatePlayerRequest;
import com.example.game_backend_api.model.Player;
import com.example.game_backend_api.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public PlayerService(PlayerRepository playerRepository, PasswordEncoder passwordEncoder,  JwtUtil jwtUtil) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Player createPlayer(String name, String email, String password)
    {
        if(playerRepository.existsByEmail(email))
        {
            logger.warn("This email already exists: {}", email);
            throw new IllegalArgumentException("This email already exists!");
        }

        if(playerRepository.existsByUsername(name))
        {
            logger.warn("This username already exists: {}", name);
            throw new IllegalArgumentException("This username already exists!");
        }

        Player player = new Player(name, email);
        String hashedPassword = passwordEncoder.encode(password);
        player.setPassword(hashedPassword);
        logger.info("Player created: {}", player.getUsername());
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

    public Player updatePlayer(String username, UpdatePlayerRequest request)
    {
        Player existingPlayer = playerRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find player: " + username));

        existingPlayer.setUsername(request.getUsername());
        existingPlayer.setEmail(request.getEmail());
        return playerRepository.save(existingPlayer);
    }

    public String deletePlayerByUsername(String username) {
        Player existingPlayer = playerRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find player: " + username));

        playerRepository.delete(existingPlayer);
        return "Player " + username + " deleted!";
    }

    public String login(String username, String password)
    {
        Player player = playerRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Player name or password not exist"));

        if (!passwordEncoder.matches(password, player.getPassword())){
            throw new IllegalArgumentException("Player name or password not exist");
        }

        return jwtUtil.generateToken(player.getUsername());
    }
}
