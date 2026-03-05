package org.example.DTO;

import org.example.domain.Companheiro;
import org.example.domain.ENUM.ClasseEspecie;

public record CompanheiroResponseDTO(
        Long id,
        String nome,
        ClasseEspecie especie,
        Integer lealdade
) {
    public static CompanheiroResponseDTO from(Companheiro c) {
        return new CompanheiroResponseDTO(
                c.getId(),
                c.getNome(),
                c.getEspecie(),
                c.getLealdade()
        );
    }
}