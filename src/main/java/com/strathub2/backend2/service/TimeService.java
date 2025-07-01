package com.strathub2.backend2.service;

import java.util.List;
import java.util.Optional;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.strathub2.backend2.dto.TimeDTO;
import com.strathub2.backend2.entity.Time;
import com.strathub2.backend2.repository.TimeRepository;

@Service
public class TimeService {

    private final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @Transactional(readOnly = true)
    public TimeDTO findById(Long id){
        Optional<Time> timeOptional = timeRepository.findById(id);
        if (timeOptional.isEmpty()) {
            throw new RuntimeException("Time não encontrado com o ID: " + id);
        }
        return new TimeDTO(timeOptional.get());
    }

    @Transactional
    public TimeDTO save(TimeDTO timeDTO) {
        Time time = new Time();
        BeanUtils.copyProperties(timeDTO, time);
        Time saved = timeRepository.save(time);
        return new TimeDTO(saved);
    }

    // Se quiser listar todos os times
    @Transactional(readOnly = true)
    public List<TimeDTO> findAll() {
        return timeRepository.findAll()
                .stream()
                .map(TimeDTO::new)
                .toList();
    }

    @Transactional
    public TimeDTO updateByName(String nome, TimeDTO timeDTO) {
        // Buscar o time no banco de dados pelo nome
        Optional<Time> optionalTime = timeRepository.findByNome(nome);

        // Verificar se o time foi encontrado
        Time existingTime = optionalTime.orElseThrow(() -> new EntityNotFoundException("Time não encontrado com o nome: " + nome));

        // Atualizar os campos do time com os dados do DTO
        BeanUtils.copyProperties(timeDTO, existingTime, "id");  // "id" não será copiado, para não sobrescrever o ID do time existente

        // Salvar o time atualizado no banco de dados
        Time updatedTime = timeRepository.save(existingTime);

        // Retornar o TimeDTO do time atualizado
        return new TimeDTO(updatedTime);
    }

@Transactional
public void deleteById(Long id) {
        timeRepository.deleteById(id);
    }

}
