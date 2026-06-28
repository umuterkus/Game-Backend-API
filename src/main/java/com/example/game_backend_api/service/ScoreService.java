package com.example.game_backend_api.service;

import com.example.game_backend_api.model.Player;
import com.example.game_backend_api.model.Score;
import com.example.game_backend_api.repository.PlayerRepository;
import com.example.game_backend_api.repository.ScoreRepository;
import org.springframework.stereotype.Service;

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


}
