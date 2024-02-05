package com.boardcamp.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.boardcamp.api.model.CustomerModel;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    boolean existsByCpf(String cpf);

    Optional<CustomerModel> findById(Long id);
}
