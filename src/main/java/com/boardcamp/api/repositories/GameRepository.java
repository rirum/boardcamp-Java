package com.boardcamp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boardcamp.api.model.GamesModel;

public interface GameRepository extends JpaRepository<GamesModel, Long> {
    boolean existsByName(String name);
}
