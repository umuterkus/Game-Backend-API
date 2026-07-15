package com.example.game_backend_api.service;

import com.example.game_backend_api.model.Player;
import com.example.game_backend_api.model.Score;
import com.example.game_backend_api.repository.PlayerRepository;
import com.example.game_backend_api.repository.ScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final PlayerRepository playerRepository;

    public ScoreService(ScoreRepository scoreRepository , PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.scoreRepository = scoreRepository;
    }

    public Score addScore(int score, String gameMode, Player player)
    {
        if (score > player.getTotalScore()) {
            player.setTotalScore(score);
            playerRepository.save(player);
        }
        Score scoreObject = new Score(score, gameMode, player);
        return scoreRepository.save(scoreObject);
    }

    public List<Score> getScoresByPlayerId(Long playerId)
    {
        if(playerRepository.existsById(playerId))
        {
            return scoreRepository.findByPlayerId(playerId);

        } else
        {
            throw new IllegalArgumentException(String.format("Cannot find player with id: %s", playerId));
        }

    }

    public List<Score> getLeaderboard() {
        return scoreRepository.findByOrderByScoreDesc();
    }



}
