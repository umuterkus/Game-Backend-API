package com.example.game_backend_api.repository;

import com.example.game_backend_api.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long>
{
    boolean existsByUsername(String username);
    boolean existsByDeviceId(String deviceId);
    Optional<Player> findByDeviceId(String deviceId);
    List<Player> findAllByOrderByTotalScoreDesc();
    Optional<Player> findByUsername(String username);



}