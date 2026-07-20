package com.example.game_backend_api.service;
import com.example.game_backend_api.config.JwtUtil;
import com.example.game_backend_api.dto.LeaderboardEntry;
import com.example.game_backend_api.dto.UpdatePlayerRequest;
import com.example.game_backend_api.model.Player;
import com.example.game_backend_api.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class PlayerService
{
    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);
    private final PlayerRepository playerRepository;
    private final JwtUtil jwtUtil;

    public PlayerService(PlayerRepository playerRepository, JwtUtil jwtUtil)
    {
        this.playerRepository = playerRepository;
        this.jwtUtil = jwtUtil;
    }

    public String deviceLogin(String deviceId, String username) {
        Player player = playerRepository.findByDeviceId(deviceId)
                .orElseGet(() ->
                {
                    Player newPlayer = new Player(deviceId, username);
                    logger.info("New player registered from device: {}", deviceId);
                    return playerRepository.save(newPlayer);
                });
        logger.info("Device login successful: {}", player.getDeviceId());

        return jwtUtil.generateToken(player.getDeviceId());
    }


//

    public Player getPlayerByDeviceId(String deviceId)
    {
        return playerRepository.findByDeviceId(deviceId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find player with deviceId: " + deviceId));
    }

    public Player updatePlayer(String deviceId, UpdatePlayerRequest request)
    {
        Player existingPlayer = playerRepository.findByDeviceId(deviceId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find player with deviceId: " + deviceId));
        String oldUsername = existingPlayer.getUsername();
        existingPlayer.setUsername(request.getUsername());

        logger.info("Username updated: {} -> {}", oldUsername, request.getUsername());
        return playerRepository.save(existingPlayer);
    }

//    public String deletePlayerByDeviceId(String deviceId)
//    {
//        Player existingPlayer = playerRepository.findByDeviceId(deviceId)
//                .orElseThrow(() -> new IllegalArgumentException("Cannot find player with deviceId: " + deviceId));
//
//        playerRepository.delete(existingPlayer);
//        logger.info("Player deleted: deviceId={}", deviceId);
//        return "Player deleted!";
//    }

//   public List<Player> getPlayers()
//    {
//        return playerRepository.findAll();
//    }
}