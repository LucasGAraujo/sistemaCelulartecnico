package org.example.DTO.audit;

import java.time.OffsetDateTime;

public record AuditEntryResponseDTO(
        Long id,
        Long organizacaoId,
        Long actorUserId,
        Long actorApiKeyId,
        String action,
        String entitySchema,
        String entityName,
        OffsetDateTime occurredAt,
        String diff
) {
    public static AuditEntryResponseDTO from(org.example.domain.audit.AuditEntry a) {
        return new AuditEntryResponseDTO(
                a.getId(),
                a.getOrganizacao() != null ? a.getOrganizacao().getId() : null,
                a.getActorUser() != null ? a.getActorUser().getId() : null,
                a.getActorApiKey() != null ? a.getActorApiKey().getId() : null,
                a.getAction(),
                a.getEntitySchema(),
                a.getEntityName(),
                a.getOccurredAt(),
                a.getDiff()
        );
    }
}