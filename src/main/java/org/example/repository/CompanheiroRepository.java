package org.example.repository;

import org.example.dataset.FakeDatabase;
import org.example.domain.Companheiro;
import org.springframework.stereotype.Repository;

@Repository
public class CompanheiroRepository {

    public Companheiro save(Companheiro companheiro) {
        if (companheiro.getId() == null) {
            companheiro.setId(FakeDatabase.nextCompanheiroId++);
        }
        return companheiro;
    }

    public void delete(Companheiro companheiro) {
        if (companheiro.getAventureiro() != null) {
            companheiro.getAventureiro().setCompanheiro(null);
        }
    }
}