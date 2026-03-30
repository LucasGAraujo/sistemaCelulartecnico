package org.example.DTO.audit;

import java.time.OffsetDateTime;

public record OrganizacaoResponseDTO(
        Long id,
        String nome,
        boolean ativo,
        OffsetDateTime createdAt
) {
    public static OrganizacaoResponseDTO from(org.example.domain.audit.Organizacao o) {
        return new OrganizacaoResponseDTO(
                o.getId(),
                o.getNome(),
                o.isAtivo(),
                o.getCreatedAt()
        );
    }
}