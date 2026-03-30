package org.example.DTO;

import java.math.BigDecimal;

public record RankingAventureiroDTO(
        String nomeAventureiro,
        Long totalParticipacoes,
        BigDecimal somaRecompensas,
        Long totalDestaques
) {}