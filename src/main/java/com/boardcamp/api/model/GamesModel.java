package com.boardcamp.api.model;

import java.math.BigDecimal;

import com.boardcamp.api.dto.GamesDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GamesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private int stockTotal;
    private int pricePerDay;

    // Construtores

    public GamesModel() {
        // Construtor vazio para JPA
    }

    public GamesModel(String name, String image, int stockTotal, int pricePerDay) {
        this.name = name;
        this.image = image;
        this.stockTotal = stockTotal;
        this.pricePerDay = pricePerDay;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(int stockTotal) {
        this.stockTotal = stockTotal;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
