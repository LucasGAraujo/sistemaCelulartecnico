package org.example.repository;

import org.example.dataset.FakeDatabase;
import org.example.domain.Aventureiro;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AventureiroRepository {

    public Aventureiro save(Aventureiro aventureiro) {
        if (aventureiro.getId() == null) {
            aventureiro.setId(FakeDatabase.nextAventureiroId++);
            FakeDatabase.aventureiros.add(aventureiro);
        }
        return aventureiro;
    }

    public Optional<Aventureiro> findById(Long id) {
        return FakeDatabase.aventureiros.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    public List<Aventureiro> findAll() {
        return FakeDatabase.aventureiros;
    }
}