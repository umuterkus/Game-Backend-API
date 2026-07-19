package com.example.game_backend_api.repository;

import com.example.game_backend_api.dto.LeaderboardEntry;
import com.example.game_backend_api.model.Score;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long>
{
    List<Score> findByPlayerId(Long playerId);


    @Query("SELECT new com.example.game_backend_api.dto.LeaderboardEntry(s.player.username, MAX(s.points)) " +
            "FROM Score s " +
            "WHERE s.playedAt > :startDate " +
            "GROUP BY s.player.id, s.player.username " +
            "ORDER BY MAX(s.points) DESC")
    List<LeaderboardEntry> findLeaderboard(@Param("startDate") LocalDateTime startDate, Pageable pageable );

}