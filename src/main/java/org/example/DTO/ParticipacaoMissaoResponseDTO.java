package org.example.DTO;

import org.example.domain.ENUM.PapelMissao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ParticipacaoMissaoResponseDTO(
        Long missaoId,
        Long aventureiroId,
        PapelMissao papel,
        BigDecimal recompensaOuro,
        Boolean destaque,
        LocalDateTime dataRegistro
) {
    public static ParticipacaoMissaoResponseDTO from(org.example.domain.ParticipacaoMissao p) {
        return new ParticipacaoMissaoResponseDTO(
                p.getMissao() != null ? p.getMissao().getId() : null,
                p.getAventureiro() != null ? p.getAventureiro().getId() : null,
                p.getPapel(),
                p.getRecompensaOuro(),
                p.getDestaque(),
                p.getDataRegistro()
        );
    }
}