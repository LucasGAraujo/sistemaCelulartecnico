package org.example.Controller;

import org.example.domain.Product;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")

public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product salvar(@RequestParam Long customerId,
                          @RequestParam String nome,
                          @RequestParam String marca,
                          @RequestParam Double preco) {
        return service.salvar(customerId, nome, marca, preco);
    }

    @GetMapping
    public List<Product> listar() {
        return service.listar();
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

}