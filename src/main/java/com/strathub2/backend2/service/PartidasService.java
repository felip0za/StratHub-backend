package com.strathub2.backend2.service;

import java.util.List;
import java.util.Optional;

import com.strathub2.backend2.dto.PartidasDTO;
import com.strathub2.backend2.entity.Partidas;
import com.strathub2.backend2.repository.PartidasRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidasService {

    private final PartidasRepository partidasRepository;

    public PartidasService(PartidasRepository partidasRepository) {
        this.partidasRepository = partidasRepository;
    }

    @Transactional(readOnly = true)
    public PartidasDTO findById(Long id) {
        Optional<Partidas> partidasOptional = partidasRepository.findById(id);
        if (partidasOptional.isEmpty()) {
            throw new RuntimeException("Partida não encontrada com o ID: " + id);
        }
        return new PartidasDTO(partidasOptional.get());
    }

    @Transactional(readOnly = true)
    public PartidasDTO findByNome(String nome) {
        Partidas partida = partidasRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada"));
        return new PartidasDTO(partida);
    }

    @Transactional
    public PartidasDTO save(PartidasDTO partidasDTO) {
        Partidas partidas = new Partidas();
        BeanUtils.copyProperties(partidasDTO, partidas);
        Partidas saved = partidasRepository.save(partidas);
        return new PartidasDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<PartidasDTO> findAll() {
        return partidasRepository.findAll()
                .stream()
                .map(PartidasDTO::new)
                .toList();
    }
}
