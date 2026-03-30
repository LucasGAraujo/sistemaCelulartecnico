package org.example.DTO.audit;

import org.example.domain.audit.ApiKey;
import org.example.domain.audit.Organizacao;
import org.example.domain.audit.Usuario;

public record ApiKeyResponseDTO (
        Long id,
        Long organizacaoId,
        String nome,
        boolean ativo
){
    public static ApiKeyResponseDTO from(ApiKey a) {
        return new ApiKeyResponseDTO (
                a.getId(),
                a.getOrganizacao() != null ? a.getOrganizacao().getId() : null,
                a.getNome(),
                a.isAtivo()
        );
    }
}

