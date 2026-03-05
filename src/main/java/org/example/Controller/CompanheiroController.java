package org.example.Controller;

import org.example.DTO.CompanheiroResponseDTO;
import org.example.domain.Companheiro;
import org.example.service.CompanheiroService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aventureiros/{aventureiroId}/companheiro")
@CrossOrigin(origins = "*")
public class CompanheiroController {

    private final CompanheiroService service;

    public CompanheiroController(CompanheiroService service) {
        this.service = service;
    }

    @PutMapping
    public CompanheiroResponseDTO definirOuSubstituir(
            @PathVariable Long aventureiroId,
            @RequestBody Companheiro dados
    ) {
        Companheiro companheiro = service.definirOuSubstituir(aventureiroId, dados);
        return CompanheiroResponseDTO.from(companheiro);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long aventureiroId) {
        service.deletarCompanheiroDoAventureiro(aventureiroId);
    }
}