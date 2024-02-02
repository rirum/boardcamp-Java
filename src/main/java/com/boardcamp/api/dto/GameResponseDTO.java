package com.boardcamp.api.dto;

public class GameResponseDTO {
    private Long id;
    private String name;
    private String image;
    private int stockTotal;
    private int pricePerDay;

    // Construtores, getters e setters

    // Exemplo de construtor
    public GameResponseDTO(Long id, String name, String image, int stockTotal, int pricePerDay) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.stockTotal = stockTotal;
        this.pricePerDay = pricePerDay;
    }
}
