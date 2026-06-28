package com.example.game_backend_api.repository;

import com.example.game_backend_api.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long>
{
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}