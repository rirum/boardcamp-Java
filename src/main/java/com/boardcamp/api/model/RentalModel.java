package com.boardcamp.api.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class RentalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rentDate")
    private LocalDate rentDate;

    @Column(name = "returnDate")
    private LocalDate returnDate;

    @Column(name = "daysRented")
    private int daysRented;

    @Column(name = "originalprice")
    private int originalPrice;

    @Column(name = "delayFee")
    private int delayFee;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "gameId", nullable = false)
    private GamesModel game;

}
