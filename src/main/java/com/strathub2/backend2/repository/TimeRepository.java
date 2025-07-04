package com.strathub2.backend2.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.strathub2.backend2.entity.Time;

import java.util.Optional;

public interface TimeRepository extends JpaRepository<Time, Integer>{
    Optional<Time> findByNome(String nome);
}