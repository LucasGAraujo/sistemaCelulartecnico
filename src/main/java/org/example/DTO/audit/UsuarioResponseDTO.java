package org.example.DTO.audit;
import org.example.domain.audit.Organizacao;
import org.example.domain.audit.Usuario;

import java.time.OffsetDateTime;

public record UsuarioResponseDTO (
    Long id,
    Long organizacaoId,
    String nome,
    String email,
    String status,
    OffsetDateTime createdAt
    ) {
    public static UsuarioResponseDTO from(Usuario u) {
        return new UsuarioResponseDTO (
                u.getId(),
                u.getOrganizacao() != null ? u.getOrganizacao().getId() : null,
                u.getNome(),
                u.getEmail(),
                u.getStatus(),
                u.getCreatedAt()
        );
    }
}