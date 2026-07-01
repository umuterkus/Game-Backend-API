package com.example.game_backend_api.repository;

import com.example.game_backend_api.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long>
{
    List<Score> findByPlayerId(Long playerId);


}