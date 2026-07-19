package com.example.game_backend_api.service;

import com.example.game_backend_api.model.Player;
import com.example.game_backend_api.model.Score;
import com.example.game_backend_api.repository.PlayerRepository;
import com.example.game_backend_api.repository.ScoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ScoreRepository scoreRepository;

    @InjectMocks
    private ScoreService scoreService;

    @InjectMocks
    private PlayerService playerService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPlayer_shouldThrowException_whenEmailAlreadyExists() {
        when(playerRepository.existsByEmail("asd@mail.com")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            playerService.createPlayer("Asd", "asd@mail.com", "asd123");
        });
    }

    @Test
    void createPlayer_shouldThrowException_whenUsernameAlreadyExists() {
        when(playerRepository.existsByUsername("Ayse")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            playerService.createPlayer("Ayse", "ayse@mail.com", "sifre123");
        });
    }


    @Test
    void createPlayer_shouldThrowException_whenIdAlreadyNotExists() {
        when(playerRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            playerService.getPlayerById(99L);
        });

    }


}