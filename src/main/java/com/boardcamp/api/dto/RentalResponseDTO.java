package com.boardcamp.api.dto;

import java.time.LocalDate;

import com.boardcamp.api.model.RentalModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalResponseDTO {
    private Long id;
    private LocalDate rentDate;
    private int daysRented;
    private LocalDate returnDate;
    private int originalPrice;
    private int delayFee;
    private CustomerResponseDTO customer;
    private GameResponseDTO game;

    public RentalResponseDTO(RentalModel rentalModel) {
        this.id = rentalModel.getId();
        this.rentDate = rentalModel.getRentDate();
        this.daysRented = rentalModel.getDaysRented();
        this.returnDate = rentalModel.getReturnDate();
        this.originalPrice = rentalModel.getOriginalPrice();
        this.delayFee = rentalModel.getDelayFee();
        this.customer = new CustomerResponseDTO(rentalModel.getCustomer());
        this.game = new GameResponseDTO(rentalModel.getGame());
    }
}
