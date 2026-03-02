package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.domain.Customer;
import org.example.domain.Product;
import org.example.repository.CustomerRepository;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CustomerRepository customerRepository;
    public ProductService(ProductRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Product salvar(Long customerId, String nome, String marca, Double preco) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Product product = new Product(nome, marca, preco, customer);
        return repository.save(product);
    }

    public List<Product> listar() {
        return repository.findAll();
    }
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
