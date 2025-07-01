package com.strathub2.backend2.repository;

import com.strathub2.backend2.entity.Partidas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartidasRepository extends JpaRepository<Partidas, Long> {
    Optional<Partidas> findByNome(String nome);
}
