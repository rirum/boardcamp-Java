
package com.boardcamp.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
    private TestRestTemplate restTemplate;

    @Autowired
    private GameRepository gameRepository;

    @AfterEach
    @BeforeEach
    void cleanUpDatabase(){
        gameRepository.deleteAll();
    }

    @Test
    void givenValidGameDTO_whenRegisteringAGame_thenRegisterNewGame() {
        // Criar um DTO válido
        GamesDTO gameDTO = new GamesDTO("Nome do Jogo", "URL da imagem", 10, 100);
    
        // Enviar a requisição HTTP POST
        ResponseEntity<GamesModel> response = restTemplate.exchange(
                "/games", HttpMethod.POST, new HttpEntity<>(gameDTO), GamesModel.class);
    
        // Verificar se a resposta não é nula e se o status é OK
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    
        
    }

    @Test
    void givenRepeatedGameName_whenRegisteringAGame_thenThrowsError() {
        // Arrange
        GamesDTO game1 = new GamesDTO("Dungeons & Dragons", "image1.jpg", 10, 1500);
        GamesDTO game2 = new GamesDTO("Dungeons & Dragons", "image2.jpg", 5, 1200);

        // Act
        ResponseEntity<Void> response1 = restTemplate.postForEntity("/games", game1, Void.class);
        ResponseEntity<Void> response2 = restTemplate.postForEntity("/games", game2, Void.class);

        // Assert
        assertEquals(HttpStatus.CREATED, response1.getStatusCode());
        assertEquals(HttpStatus.CONFLICT, response2.getStatusCode());
    }

    @Test
    void givenNullName_whenRegisteringAGame_thenThrowsError() {
        // Arrange
        GamesDTO gameDTO = new GamesDTO(null, "image.jpg", 10, 1500);

        // Act & Assert
        ResponseEntity<Void> response = restTemplate.postForEntity("/games", gameDTO, Void.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}