package org.example.DTO.audit;
import org.example.domain.audit.Organizacao;
import org.example.domain.audit.Role;


public record RoleResponseDTO (
        Long id,
        Long organizacaoId,
        String nome,
        String descricao
){
    public static RoleResponseDTO from (Role r) {
        return new RoleResponseDTO(
                r.getId(),
                r.getOrganizacao() != null ? r.getOrganizacao().getId() : null,
                r.getNome(),
                r.getDescricao()

        );
    }
}