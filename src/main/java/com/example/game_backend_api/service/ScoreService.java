package com.example.game_backend_api.service;

import com.example.game_backend_api.dto.LeaderboardEntry;
import com.example.game_backend_api.model.Player;
import com.example.game_backend_api.model.Score;
import com.example.game_backend_api.repository.PlayerRepository;
import com.example.game_backend_api.repository.ScoreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<LeaderboardEntry> getLeaderboard(String filter) {

        LocalDateTime startDate;

        switch (filter) {
            case "daily" -> startDate = LocalDateTime.now().minusDays(1);
            case "weekly" -> startDate = LocalDateTime.now().minusWeeks(1);
            case "monthly" -> startDate = LocalDateTime.now().minusMonths(1);
            case "all" -> startDate = LocalDateTime.of(2000, 1, 1, 0, 0);
            default -> throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid filter value. Allowed values are: daily, weekly, monthly, all."

            );
        }

        List<LeaderboardEntry> leaderboardEntries = scoreRepository.findLeaderboard(startDate);

        for (int i = 0; i < leaderboardEntries.size(); i++) {
            leaderboardEntries.get(i).setRank(i + 1);
        }

        return leaderboardEntries;
    }
}

