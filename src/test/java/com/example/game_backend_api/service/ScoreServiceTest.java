package com.example.game_backend_api.service;

import com.example.game_backend_api.dto.LeaderboardEntry;
import com.example.game_backend_api.repository.PlayerRepository;
import com.example.game_backend_api.repository.ScoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ScoreServiceTest {


    @Mock
    private ScoreRepository scoreRepository;

    @Mock
    private PlayerRepository playerRepository;


    @InjectMocks
    private ScoreService scoreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getLeaderboard_shouldThrowException_whenFilterIsInvalid() {

        assertThrows(ResponseStatusException.class, () -> {
            scoreService.getLeaderboard("daily1231321");
        });
    }

    @Test
    void getLeaderboard_shouldReturnEntries_whenFilterIsDaily() {

        List<LeaderboardEntry> fakeData = List.of(
                new LeaderboardEntry("AAAA", 500),
                new LeaderboardEntry("BBBB", 300)
        );
        when(scoreRepository.findLeaderboard(any(LocalDateTime.class))).thenReturn(fakeData);

        List<LeaderboardEntry> result = scoreService.getLeaderboard("daily");


        assertEquals(2, result.size());
    }


    @Test
    void getLeaderboard_shouldAssignRanksCorrectly() {
        List<LeaderboardEntry> fakeData = List.of(
                new LeaderboardEntry("AAA", 500),
                new LeaderboardEntry("BBB", 300),
                new LeaderboardEntry("CCC", 100)
        );
        when(scoreRepository.findLeaderboard(any(LocalDateTime.class))).thenReturn(fakeData);

        List<LeaderboardEntry> result = scoreService.getLeaderboard("weekly");

        assertEquals(1, result.get(0).getRank());

        assertEquals(2, result.get(1).getRank());

        assertEquals(3, result.get(2).getRank());
    }

    @Test
    void getLeaderboard_shouldNotThrow_whenFilterIsAll() {
        when(scoreRepository.findLeaderboard(any(LocalDateTime.class))).thenReturn(List.of());

        assertDoesNotThrow(() -> {
            scoreService.getLeaderboard("all");
        });
    }
}
