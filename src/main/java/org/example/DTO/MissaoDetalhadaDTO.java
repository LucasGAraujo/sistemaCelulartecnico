package org.example.DTO;

import java.util.List;

public record MissaoDetalhadaDTO(
        MissaoResponseDTO missao,
        List<ParticipanteResumoDTO> participantes
) {}

