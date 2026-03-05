package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.ENUM.ClasseEspecie;

@Getter
@Setter
public class Companheiro {

    private Long id;

    private String nome;

    private ClasseEspecie especie;

    @Min(0)
    @Max(100)
    private Integer lealdade;

    private Aventureiro aventureiro;

    public Companheiro() {}

    public Companheiro(String nome, ClasseEspecie especie, Integer lealdade, Aventureiro aventureiro) {
        this.nome = nome;
        this.especie = especie;
        this.lealdade = lealdade;
        this.aventureiro = aventureiro;
    }
}