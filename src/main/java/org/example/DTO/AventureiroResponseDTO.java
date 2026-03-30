package org.example.DTO;
import org.example.domain.ENUM.ClasseAventureiro;

import java.time.LocalDateTime;

public record AventureiroResponseDTO(
        Long id,
        Long organizacaoId,
        Long usuarioCadastroId,
        String nome,
        ClasseAventureiro classe,
        Integer nivel,
        Boolean ativo,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao,
        Long companheiroId
) {
    public static AventureiroResponseDTO from(org.example.domain.Aventureiro a) {
        return new AventureiroResponseDTO(
                a.getId(),
                a.getOrganizacao() != null ? a.getOrganizacao().getId() : null,
                a.getUsuarioCadastro() != null ? a.getUsuarioCadastro().getId() : null,
                a.getNome(),
                a.getClasse(),
                a.getNivel(),
                a.getAtivo(),
                a.getDataCriacao(),
                a.getDataAtualizacao(),
                a.getCompanheiro() != null ? a.getCompanheiro().getId() : null
        );
    }
}