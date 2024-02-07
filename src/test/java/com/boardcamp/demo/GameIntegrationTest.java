
package com.boardcamp.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.boardcamp.api.DemoApplication;
import com.boardcamp.api.dto.GamesDTO;
import com.boardcamp.api.model.GamesModel;
import com.boardcamp.api.repositories.GameRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = DemoApplication.class)
@ActiveProfiles("test")
class GameIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private GameRepository gameRepository;

    @Test
    void givenValidGameDTO_whenRegisteringAGame_thenRegisterNewGame() {
        GamesDTO dto = new GamesDTO("name2", "image2", 2, 1500);

        // Criar um novo objeto GamesModel com os dados de GamesDTO
        GamesModel gameModel = new GamesModel(dto.getName(), dto.getImage(), dto.getStockTotal(), dto.getPricePerDay());

        // Chamar o método save com o objeto GamesModel
        GamesModel savedGame = gameRepository.save(gameModel);

        // Realizar as verificações necessárias
        assertEquals(dto.getName(), savedGame.getName());
        assertEquals(dto.getImage(), savedGame.getImage());
        assertEquals(dto.getStockTotal(), savedGame.getStockTotal());
        assertEquals(dto.getPricePerDay(), savedGame.getPricePerDay());
    }

}
