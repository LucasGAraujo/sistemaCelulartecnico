package org.example.DTO;

public record ParticipanteResumoDTO(
        Long aventureiroId,
        String nomeAventureiro,
        org.example.domain.ENUM.PapelMissao papel,
        java.math.BigDecimal recompensaOuro,
        Boolean destaque
) {}