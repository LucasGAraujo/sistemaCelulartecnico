package org.example.repository;

import org.example.domain.Aventureiro;
import org.example.domain.Companheiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanheiroRepository extends JpaRepository<Companheiro, Long> {
}