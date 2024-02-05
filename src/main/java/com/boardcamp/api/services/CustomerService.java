package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dto.CustomerDTO;
import com.boardcamp.api.exceptions.ConflictException;
import com.boardcamp.api.exceptions.EmptyFieldException;
import com.boardcamp.api.model.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerModel createCustomer(CustomerDTO dto) {
        validateFields(dto);
        validateCpf(dto.getCpf());

        CustomerModel customer = new CustomerModel();
        customer.setName(dto.getName());
        customer.setCpf(dto.getCpf());

        return customerRepository.save(customer);
    }

    private void validateFields(CustomerDTO dto) {
        if (dto.getName() == null || dto.getName().isEmpty() || dto.getCpf() == null || dto.getCpf().isEmpty()) {
            throw new EmptyFieldException("Name and CPF must not be null or empty");
        }
    }

    private void validateCpf(String cpf) {
        if (customerRepository.existsByCpf(cpf)) {
            throw new ConflictException("Customer with this CPF already exists");
        }
    }
}
