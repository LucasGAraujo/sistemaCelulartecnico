package org.example.Controller.DTO;
import org.example.domain.Order;
import java.time.LocalDateTime;

public record OrderResponseDTO(
        Long id,
        String descricaoServico,
        Double valorServico,
        boolean finalizada,
        LocalDateTime dataCriacao,
        String nomeCliente,
        String nomeProduto
) {
    public OrderResponseDTO(Order order) {
        this(
                order.getId(),
                order.getDescricaoServico(),
                order.getValorServico(),
                order.isFinalizada(),
                order.getDataCriacao(),
                order.getCustomer().getNome(),
                order.getProduct().getNome()
        );
    }
}