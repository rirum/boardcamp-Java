package com.boardcamp.api.dto;

public class RentalDTO {

    private Long customerId;
    private Long gameId;
    private int daysRented;

    // Construtores, getters e setters

    // Exemplo de construtor
    public RentalDTO(Long customerId, Long gameId, int daysRented) {
        this.customerId = customerId;
        this.gameId = gameId;
        this.daysRented = daysRented;
    }

    // Getters e setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }
}
