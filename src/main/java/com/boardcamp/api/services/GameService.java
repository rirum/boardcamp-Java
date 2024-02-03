package com.boardcamp.api.services;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dto.GameResponseDTO;
import com.boardcamp.api.dto.GamesDTO;
import com.boardcamp.api.exceptions.EmptyNameException;
import com.boardcamp.api.exceptions.GameConflictException;
import com.boardcamp.api.exceptions.NegativeValueException;
import com.boardcamp.api.model.GamesModel;
import com.boardcamp.api.repositories.GameRepository;

import jakarta.transaction.Transactional;



@Service
public class GameService {
    private GameRepository gameRepository;

    GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    
 public List<GameResponseDTO> getAllGames() {
        List<GamesModel> games = gameRepository.findAll();
        return games.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private GameResponseDTO convertToDto(GamesModel gameModel) {
        // Implemente a conversão de GamesModel para GameResponseDTO aqui
        return new GameResponseDTO(
                gameModel.getId(),
                gameModel.getName(),
                gameModel.getImage(),
                gameModel.getStockTotal(),
                gameModel.getPricePerDay()
        );
    }


    @Transactional
    public GameResponseDTO createGame(GamesDTO dto) {

        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new EmptyNameException("Name must not be null or empty");
        }
        
        
        // Verificação de números maiores que 0
        if (dto.getStockTotal() <= 0 || dto.getPricePerDay() <= 0) {
            // Lança uma exceção, você pode personalizar a exceção conforme necessário
            throw new NegativeValueException("stockTotal and pricePerDay must be greater than 0");
        }

        // Verificação de nome existente
        if (gameRepository.existsByName(dto.getName())) {
            // Nome já existe, retorna status 409 (CONFLICT)
            throw new GameConflictException("Game with the same name already exists");
        }

        // Criação do modelo de jogo
        GamesModel gameModel = new GamesModel(dto.getName(), dto.getImage(), dto.getStockTotal(), dto.getPricePerDay());

        // Salvando o jogo no banco de dados
        GamesModel savedGame = gameRepository.save(gameModel);

        // Criando e retornando a resposta DTO
        return new GameResponseDTO(
            savedGame.getId(),
            savedGame.getName(),
            savedGame.getImage(),
            savedGame.getStockTotal(),
            savedGame.getPricePerDay()
        );
    }
  
}