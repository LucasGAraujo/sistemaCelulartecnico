package org.example.DTO;

import org.example.domain.ENUM.ClasseEspecie;

public record CompanheiroResponseDTO(
        Long id,
        Long aventureiroId,
        String nome,
        ClasseEspecie especie,
        Integer lealdade
) {
    public static CompanheiroResponseDTO from(org.example.domain.Companheiro c) {
        return new CompanheiroResponseDTO(
                c.getId(),
                c.getAventureiro() != null ? c.getAventureiro().getId() : null,
                c.getNome(),
                c.getEspecie(),
                c.getLealdade()
        );
    }
}