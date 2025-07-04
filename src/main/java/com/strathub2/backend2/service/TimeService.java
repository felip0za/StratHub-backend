package com.strathub2.backend2.service;

import com.strathub2.backend2.dto.TimeDTO;
import com.strathub2.backend2.entity.Time;
import com.strathub2.backend2.repository.TimeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TimeService {

    private final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @Transactional(readOnly = true)
    public TimeDTO findById(Integer id) {
        Time time = timeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com o ID: " + id));
        return new TimeDTO(time);
    }

    @Transactional(readOnly = true)
    public List<TimeDTO> findAll() {
        return timeRepository.findAll()
                .stream()
                .map(TimeDTO::new)
                .toList();
    }

    @Transactional
    public TimeDTO save(TimeDTO timeDTO) {
        Time time = new Time();
        copyDtoToEntity(timeDTO, time);
        Time savedTime = timeRepository.save(time);
        return new TimeDTO(savedTime);
    }

    @Transactional
    public TimeDTO updateByName(String nome, TimeDTO timeDTO) {
        Time existingTime = timeRepository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com o nome: " + nome));

        copyDtoToEntity(timeDTO, existingTime);
        Time updatedTime = timeRepository.save(existingTime);
        return new TimeDTO(updatedTime);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!timeRepository.existsById(id)) {
            throw new EntityNotFoundException("Time não encontrado com o ID: " + id);
        }
        timeRepository.deleteById(id);
    }

    /**
     * Método auxiliar para copiar os dados do DTO para a entidade Time.
     */
    private void copyDtoToEntity(TimeDTO dto, Time entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setImagemBase64(dto.getImagemBase64());
    }
}
