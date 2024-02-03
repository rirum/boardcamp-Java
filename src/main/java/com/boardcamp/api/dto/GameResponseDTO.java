package com.boardcamp.api.dto;

public class GameResponseDTO {
    private Long id;
    private String name;
    private String image;
    private int stockTotal;
    private int pricePerDay;


    public GameResponseDTO(Long id, String name, String image, int stockTotal, int pricePerDay) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.stockTotal = stockTotal;
        this.pricePerDay = pricePerDay;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getStockTotal() {
        return stockTotal;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }
}
