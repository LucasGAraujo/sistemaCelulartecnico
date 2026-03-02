package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_order_product")
    )
    @JsonIgnoreProperties({"orders", "customer", "hibernateLazyInitializer", "handler"})
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "customer_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_order_customer")
    )
    @JsonIgnoreProperties({"orders", "products", "hibernateLazyInitializer", "handler"})
    private Customer customer;

    @Column(nullable = false, length = 150)
    @NotBlank
    private String descricaoServico;

    @Column(nullable = false)
    @Positive
    private Double valorServico;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private boolean finalizada = false;

    protected Order() {}

    public Order(Product product, Customer customer, String descricao, Double valor) {
        this.product = product;
        this.customer = customer;
        this.descricaoServico = descricao;
        this.valorServico = valor;
        this.dataCriacao = LocalDateTime.now();
    }

    public void finalizar() {
        if (this.finalizada) throw new IllegalArgumentException("Ordem já está finalizada");
        this.finalizada = true;
    }

    public void alterarDescricao(String novaDescricao) {
        if (this.finalizada) throw new IllegalStateException("Não é possível alterar uma ordem finalizada");
        this.descricaoServico = novaDescricao;
    }

    public void alterarValor(Double novoValor) {
        if (this.finalizada) throw new IllegalStateException("Não é possível alterar valor de ordem finalizada");
        if (novoValor <= 0) throw new IllegalArgumentException("Valor deve ser maior que zero");
        this.valorServico = novoValor;
    }
}