package org.example.DTO;

public record AventureiroPerfilCompletoDTO(
        AventureiroResponseDTO aventureiro,
        CompanheiroResponseDTO companheiro,
        Long totalParticipacoes,
        MissaoResponseDTO ultimaMissao
) {}