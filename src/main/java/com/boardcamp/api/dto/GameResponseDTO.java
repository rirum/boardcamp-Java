package com.boardcamp.api.dto;

import com.boardcamp.api.model.GamesModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameResponseDTO {
    private Long id;
    private String name;
    private String image;
    private int stockTotal;
    private int pricePerDay;

    public GameResponseDTO(GamesModel gamesModel) {
        this.id = gamesModel.getId();
        this.name = gamesModel.getName();
        this.image = gamesModel.getImage();
        this.stockTotal = gamesModel.getStockTotal();
        this.pricePerDay = gamesModel.getPricePerDay();
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
