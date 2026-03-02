package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_customer_email",
                        columnNames = "email"
                )
        }
)
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    @NotBlank
    @Size(max = 120)
    private String nome;

    @Column(nullable = false, length = 120)
    @NotBlank
    @Email
    @Size(max = 120)
    private String email;

    @Column(nullable = false, length = 20)
    @NotBlank
    @Size(max = 20)
    private String telefone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    protected Customer() {}

    public Customer(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public void alterarTelefone(String novoTelefone) {
        if (novoTelefone == null || novoTelefone.isBlank()) throw new IllegalArgumentException("Telefone inválido");
        this.telefone = novoTelefone;
    }

    public void alterarEmail(String novoEmail) {
        if (novoEmail == null || novoEmail.isBlank()) throw new IllegalArgumentException("Email inválido");
        this.email = novoEmail;
    }

    public void alterarNome(String novoNome) {
        if (novoNome == null || novoNome.isBlank()) throw new IllegalArgumentException("Nome inválido");
        this.nome = novoNome;
    }
}