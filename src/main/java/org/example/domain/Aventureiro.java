package org.example.domain;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.ENUM.ClasseAventureiro;

@Getter
@Setter
public class Aventureiro {

    private Long id;

    private String nome;

    private ClasseAventureiro classe;

    @Min(1)
    private Integer nivel;

    private Boolean ativo = true;

    private Companheiro companheiro;
}