package com.example.game_backend_api.repository;

import com.example.game_backend_api.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long>
{
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    List<Player> findAllByOrderByTotalScoreDesc();

}