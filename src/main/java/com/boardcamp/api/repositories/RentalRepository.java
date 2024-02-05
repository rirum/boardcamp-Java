package com.boardcamp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.boardcamp.api.model.RentalModel;

public interface RentalRepository extends JpaRepository<RentalModel, Long> {
    long countByGameIdAndReturnDateIsNull(Long gameId);
}
