package com.boardcamp.api.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boardcamp.api.dto.RentalDTO;
import com.boardcamp.api.exceptions.EmptyFieldException;
import com.boardcamp.api.exceptions.GameUnavailableException;
import com.boardcamp.api.exceptions.InvalidGameIdException;
import com.boardcamp.api.exceptions.NegativeValueException;
import com.boardcamp.api.model.CustomerModel;
import com.boardcamp.api.model.GamesModel;
import com.boardcamp.api.model.RentalModel;
import com.boardcamp.api.repositories.RentalRepository;

@Service
public class RentalService {

    private RentalRepository rentalRepository;
    private CustomerService customerService;
    private GameService gameService;

    @Autowired
    public RentalService(RentalRepository rentalRepository, CustomerService customerService, GameService gameService) {
        this.rentalRepository = rentalRepository;
        this.customerService = customerService;
        this.gameService = gameService;
    }

    public RentalModel createRental(RentalDTO rentalDTO) {

        validateRentalDTO(rentalDTO);

        GamesModel game = gameService.getGameById(rentalDTO.getGameId());

        CustomerModel customer = customerService.getCustomerById(rentalDTO.getCustomerId());

        validateGameAvailability(game);

        LocalDate rentDate = LocalDate.now();
        int originalPrice = calculateOriginalPrice(game.getPricePerDay(), rentalDTO.getDaysRented());

        RentalModel rental = new RentalModel();
        rental.setRentDate(rentDate);
        rental.setReturnDate(null);
        rental.setDaysRented(rentalDTO.getDaysRented());
        rental.setOriginalPrice(originalPrice);
        rental.setDelayFee(0);
        rental.setCustomer(customer);
        rental.setGame(game);

        return rentalRepository.save(rental);
    }

    private void validateRentalDTO(RentalDTO rentalDTO) {
        if (rentalDTO.getDaysRented() <= 0) {
            throw new NegativeValueException("DaysRented should be greater than 0");
        }

        if (rentalDTO.getGameId() <= 0) {
            throw new InvalidGameIdException("gameId should be greater than 0");
        }

        if (rentalDTO.getCustomerId() == null || rentalDTO.getGameId() == null) {
            throw new EmptyFieldException("CustomerId and GameId cannot be null");
        }
    }

    private void validateGameAvailability(GamesModel game) {
        int stockTotal = game.getStockTotal();
        long rentedCount = rentalRepository.countByGameIdAndReturnDateIsNull(game.getId());

        if (rentedCount >= stockTotal) {
            throw new GameUnavailableException("No availability for the selected game.");
        }
    }

    private int calculateOriginalPrice(int pricePerDay, int daysRented) {
        return pricePerDay * daysRented;
    }
}
