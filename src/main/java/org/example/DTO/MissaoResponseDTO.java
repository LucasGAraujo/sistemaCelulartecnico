package org.example.DTO;

import org.example.domain.ENUM.NivelPerigo;
import org.example.domain.ENUM.StatusMissao;

import java.time.LocalDateTime;

public record MissaoResponseDTO(
        Long id,
        Long organizacaoId,
        String titulo,
        NivelPerigo nivelPerigo,
        StatusMissao status,
        LocalDateTime dataCriacao,
        LocalDateTime dataInicio,
        LocalDateTime dataFim
) {
    public static MissaoResponseDTO from(org.example.domain.Missao m) {
        return new MissaoResponseDTO(
                m.getId(),
                m.getOrganizacao() != null ? m.getOrganizacao().getId() : null,
                m.getTitulo(),
                m.getNivelPerigo(),
                m.getStatus(),
                m.getDataCriacao(),
                m.getDataInicio(),
                m.getDataFim()
        );
    }
}