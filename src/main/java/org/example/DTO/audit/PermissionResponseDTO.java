package org.example.DTO.audit;

public record PermissionResponseDTO(
        Long id,
        String code,
        String descricao
) {
    public static PermissionResponseDTO from(org.example.domain.audit.Permission p) {
        return new PermissionResponseDTO(
                p.getId(),
                p.getCode(),
                p.getDescricao()
        );
    }
}