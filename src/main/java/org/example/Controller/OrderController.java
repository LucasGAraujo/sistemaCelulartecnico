package org.example.Controller;

import org.example.Controller.DTO.OrderResponseDTO; // 🔥 Import adicionado!
import org.example.domain.Order;
import org.example.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponseDTO criar(@RequestParam Long productId,
                                  @RequestParam Long customerId,
                                  @RequestParam String descricao,
                                  @RequestParam Double valor) {

        Order order = service.criar(productId, customerId, descricao, valor);
        return new OrderResponseDTO(order);
    }

    @GetMapping
    public List<OrderResponseDTO> listar() {
        return service.listar().stream()
                .map(OrderResponseDTO::new)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PatchMapping("/{id}/finalizar")
    public OrderResponseDTO finalizar(@PathVariable Long id) {
        Order order = service.finalizar(id);
        return new OrderResponseDTO(order);
    }
}