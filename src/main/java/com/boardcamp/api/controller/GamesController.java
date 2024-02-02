package com.boardcamp.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dto.GameResponseDTO;
import com.boardcamp.api.dto.GamesDTO;
import com.boardcamp.api.model.GamesModel;

import com.boardcamp.api.services.GameService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/games")
public class GamesController {
    private GameService gameService;

    @Autowired
    public GamesController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<GameResponseDTO> createGame(@RequestBody @Valid GamesDTO body) {
        GameResponseDTO createdGame = gameService.createGame(body);
        return new ResponseEntity<>(createdGame, HttpStatus.CREATED);
    }
}