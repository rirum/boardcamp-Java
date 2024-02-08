package com.boardcamp.demo;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.boardcamp.api.DemoApplication;
import com.boardcamp.api.controller.GamesController;
import com.boardcamp.api.dto.GameResponseDTO;
import com.boardcamp.api.dto.GamesDTO;
import com.boardcamp.api.exceptions.NegativeValueException;
import com.boardcamp.api.model.GamesModel;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.services.GameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = DemoApplication.class)
@ExtendWith(MockitoExtension.class)
class GameUnitTest {
    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllGames() {

        GamesModel game1 = new GamesModel(1L, "Game 1", "image1.jpg", 5, 1500);
        GamesModel game2 = new GamesModel(2L, "Game 2", "image2.jpg", 3, 2000);
        List<GamesModel> gamesList = new ArrayList<>();
        gamesList.add(game1);
        gamesList.add(game2);

        when(gameRepository.findAll()).thenReturn(gamesList);

        List<GameResponseDTO> result = gameService.getAllGames();

        assertEquals(2, result.size());
        assertEquals("Game 1", result.get(0).getName());
        assertEquals("Game 2", result.get(1).getName());

    }

    @Test
    void testGetGameById() {

        Long gameId = 1L;
        GamesModel game = new GamesModel();
        game.setId(gameId);
        game.setName("Game 1");
        game.setImage("image1.jpg");
        game.setStockTotal(5);
        game.setPricePerDay(1500);

        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));

        GamesModel retrievedGame = gameService.getGameById(gameId);

        assertEquals(game, retrievedGame);
    }

    @Test
    void givenNegativeStockTotal_whenCreatingGame_thenThrowBadRequestException() {
        GamesDTO dto = new GamesDTO("nome", "imagem", -1, 1000);

        assertThrows(NegativeValueException.class, () -> gameService.createGame(dto));
    }
}
