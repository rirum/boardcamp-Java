package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dto.GameResponseDTO;
import com.boardcamp.api.dto.GamesDTO;
import com.boardcamp.api.model.GamesModel;
import com.boardcamp.api.repositories.GameRepository;

@Service
public class GameService {
    private GameRepository gameRepository;

    GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameResponseDTO createGame(GamesDTO dto) {
        GamesModel gameModel = new GamesModel(dto.getName(), dto.getImage(), dto.getStockTotal(), dto.getPricePerDay());
        GamesModel savedGame = gameRepository.save(gameModel);

        return new GameResponseDTO(
                savedGame.getId(),
                savedGame.getName(),
                savedGame.getImage(),
                savedGame.getStockTotal(),
                savedGame.getPricePerDay());
    }
}
