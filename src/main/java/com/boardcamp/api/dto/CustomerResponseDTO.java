package com.boardcamp.api.dto;

import com.boardcamp.api.model.CustomerModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String cpf;

    public CustomerResponseDTO(CustomerModel customerModel) {
        this.id = customerModel.getId();
        this.name = customerModel.getName();
        this.cpf = customerModel.getCpf();
    }
}
