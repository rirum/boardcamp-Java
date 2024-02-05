package com.boardcamp.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dto.RentalDTO;
import com.boardcamp.api.model.RentalModel;
import com.boardcamp.api.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/rentals")
public class RentalsController {

    private RentalService rentalService;

    @Autowired
    public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    public ResponseEntity<RentalModel> createRental(@RequestBody RentalDTO rentalDTO) {
        RentalModel createdRental = rentalService.createRental(rentalDTO);
        return new ResponseEntity<>(createdRental, HttpStatus.CREATED);
    }
}