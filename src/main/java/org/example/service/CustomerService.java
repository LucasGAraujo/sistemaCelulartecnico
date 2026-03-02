package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Customer;
import org.example.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer salvar(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> listar() {
        return repository.findAll();
    }
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}