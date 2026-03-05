package org.example.service;

import org.example.domain.Aventureiro;
import org.example.domain.Companheiro;
import org.example.exception.RecursoNaoEncontradoException;
import org.example.exception.RegraNegocioException;
import org.example.repository.AventureiroRepository;
import org.example.repository.CompanheiroRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanheiroService {

    private final CompanheiroRepository companheiroRepository;
    private final AventureiroRepository aventureiroRepository;

    public CompanheiroService(CompanheiroRepository companheiroRepository, AventureiroRepository aventureiroRepository) {
        this.companheiroRepository = companheiroRepository;
        this.aventureiroRepository = aventureiroRepository;
    }

    public Companheiro definirOuSubstituir(Long aventureiroId, Companheiro dados) {
        Aventureiro aventureiro = aventureiroRepository.findById(aventureiroId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aventureiro não encontrado"));

        if (dados.getNome() == null || dados.getNome().isBlank()) {
            throw new RegraNegocioException("Nome do companheiro é obrigatório");
        }
        if (dados.getEspecie() == null) {
            throw new RegraNegocioException("Espécie do companheiro é obrigatória ou inválida");
        }
        if (dados.getLealdade() == null || dados.getLealdade() < 0 || dados.getLealdade() > 100) {
            throw new RegraNegocioException("A lealdade deve estar entre 0 e 100");
        }

        Companheiro companheiro = aventureiro.getCompanheiro();

        if (companheiro == null) {
            companheiro = new Companheiro();
            companheiro.setAventureiro(aventureiro);
        }

        companheiro.setNome(dados.getNome());
        companheiro.setEspecie(dados.getEspecie());
        companheiro.setLealdade(dados.getLealdade());

        companheiro = companheiroRepository.save(companheiro);
        aventureiro.setCompanheiro(companheiro);
        aventureiroRepository.save(aventureiro);

        return companheiro;
    }

    public void deletarCompanheiroDoAventureiro(Long aventureiroId) {
        Aventureiro aventureiro = aventureiroRepository.findById(aventureiroId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aventureiro não encontrado"));

        Companheiro companheiro = aventureiro.getCompanheiro();

        if (companheiro == null) {
            throw new RecursoNaoEncontradoException("Este aventureiro não possui um companheiro para remover");
        }

        aventureiro.setCompanheiro(null);
        companheiroRepository.delete(companheiro);
        aventureiroRepository.save(aventureiro);
    }
}