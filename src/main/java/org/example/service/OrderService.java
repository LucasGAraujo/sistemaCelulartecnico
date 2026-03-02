package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.domain.Customer;
import org.example.domain.Order;
import org.example.domain.Product;
import org.example.repository.CustomerRepository;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }
    @Transactional
    public Order criar(Long productId, Long customerId, String descricao, Double valor) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Aparelho não encontrado"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (!product.getCustomer().getId().equals(customer.getId())) {
            throw new IllegalArgumentException("Erro: Este aparelho não pertence a este cliente!");
        }

        Order order = new Order(product, customer, descricao, valor);
        return orderRepository.save(order);
    }

    public List<Order> listar() {
        return orderRepository.findAll();
    }
    public void deletar(Long id) {
        orderRepository.deleteById(id);
    }
    @Transactional
    public Order finalizar(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        order.finalizar();
        return orderRepository.save(order);
    }
}