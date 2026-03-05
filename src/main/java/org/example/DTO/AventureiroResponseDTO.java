package org.example.DTO;

import org.example.domain.Aventureiro;
import org.example.domain.ENUM.ClasseAventureiro;

public record AventureiroResponseDTO(
        Long id,
        String nome,
        ClasseAventureiro classe,
        Integer nivel,
        Boolean ativo,
        CompanheiroResponseDTO companheiro
) {
    public static AventureiroResponseDTO from(Aventureiro a) {
        return new AventureiroResponseDTO(
                a.getId(),
                a.getNome(),
                a.getClasse(),
                a.getNivel(),
                a.getAtivo(),
                a.getCompanheiro() != null ? CompanheiroResponseDTO.from(a.getCompanheiro()) : null
        );
    }
}