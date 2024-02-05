package com.boardcamp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.boardcamp.api.model.CustomerModel;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    boolean existsByCpf(String cpf);
}
