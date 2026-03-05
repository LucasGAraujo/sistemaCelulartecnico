package org.example.service;

import org.example.domain.Aventureiro;
import org.example.domain.ENUM.ClasseAventureiro;
import org.example.exception.RecursoNaoEncontradoException;
import org.example.exception.RegraNegocioException;
import org.example.repository.AventureiroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AventureiroService {

    private final AventureiroRepository aventureiroRepository;

    public AventureiroService(AventureiroRepository aventureiroRepository) {
        this.aventureiroRepository = aventureiroRepository;
    }

    public Aventureiro criar(Aventureiro aventureiro) {
        if (aventureiro.getNome() == null || aventureiro.getNome().isBlank()) {
            throw new RegraNegocioException("Nome do aventureiro é obrigatório");
        }
        if (aventureiro.getClasse() == null) {
            throw new RegraNegocioException("Classe é obrigatória");
        }
        if (aventureiro.getNivel() == null || aventureiro.getNivel() < 1) {
            throw new RegraNegocioException("Nível deve ser maior ou igual a 1");
        }
        if (aventureiro.getCompanheiro() != null) {
            throw new RegraNegocioException("Não é permitido definir companheiro ao criar aventureiro");
        }

        aventureiro.setAtivo(true);

        return aventureiroRepository.save(aventureiro);
    }

    public List<Aventureiro> listar(ClasseAventureiro classe, Boolean ativo, Integer nivelMin) {
        return aventureiroRepository.findAll().stream()
                .filter(a -> classe == null || a.getClasse() == classe)
                .filter(a -> ativo == null || a.getAtivo().equals(ativo))
                .filter(a -> nivelMin == null || a.getNivel() >= nivelMin)
                .sorted((a1, a2) -> a1.getId().compareTo(a2.getId()))
                .collect(Collectors.toList());
    }

    public Aventureiro buscarPorId(Long id) {
        return aventureiroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aventureiro não encontrado"));
    }

    public Aventureiro atualizar(Long id, Aventureiro dadosAtualizados) {
        Aventureiro existente = buscarPorId(id);

        if (dadosAtualizados.getNome() == null || dadosAtualizados.getNome().isBlank()) {
            throw new RegraNegocioException("Nome não pode ser vazio");
        }
        if (dadosAtualizados.getClasse() == null) {
            throw new RegraNegocioException("Classe inválida ou não informada");
        }
        if (dadosAtualizados.getNivel() == null || dadosAtualizados.getNivel() < 1) {
            throw new RegraNegocioException("Nível deve ser maior ou igual a 1");
        }

        existente.setNome(dadosAtualizados.getNome());
        existente.setClasse(dadosAtualizados.getClasse());
        existente.setNivel(dadosAtualizados.getNivel());

        return aventureiroRepository.save(existente);
    }

    public Aventureiro encerrarVinculoGuilda(Long id) {
        Aventureiro aventureiro = buscarPorId(id);
        aventureiro.setAtivo(false);
        return aventureiroRepository.save(aventureiro);
    }

    public Aventureiro recrutarNovamente(Long id) {
        Aventureiro aventureiro = buscarPorId(id);
        if (aventureiro.getAtivo()) {
            throw new RegraNegocioException("O aventureiro já possui vínculo ativo com a guilda");
        }
        aventureiro.setAtivo(true);
        return aventureiroRepository.save(aventureiro);
    }
}