package org.example.dataset;

import org.example.domain.Aventureiro;
import org.example.domain.ENUM.ClasseAventureiro;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {

    public static final List<Aventureiro> aventureiros = new ArrayList<>();

    public static Long nextAventureiroId = 101L;
    public static Long nextCompanheiroId = 1L;

    static {
        for (int i = 1; i <= 100; i++) {
            Aventureiro a = new Aventureiro();
            a.setId((long) i);
            a.setNome("Aventureiro " + i);
            a.setClasse(ClasseAventureiro.values()[i % 5]);
            a.setNivel((i % 20) + 1);
            a.setAtivo(true);

            aventureiros.add(a);
        }
    }
}