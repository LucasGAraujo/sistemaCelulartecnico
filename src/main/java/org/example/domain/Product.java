package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Entity
@Table(name = "products")
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String nome;

    @Column(nullable = false, length = 80)
    @NotBlank
    @Size(max = 80)
    private String marca;

    @Column(nullable = false)
    @Positive
    private Double preco;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private java.util.List<Order> orders = new java.util.ArrayList<>();

    protected Product() {}

    public Product(String nome, String marca, Double preco, Customer customer) {
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.customer = customer;
    }

    public Long getCustomerId() {
        return customer != null ? customer.getId() : null;
    }
}