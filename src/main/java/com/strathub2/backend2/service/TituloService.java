package com.strathub2.backend2.service;

import com.strathub2.backend2.dto.TituloDTO;
import com.strathub2.backend2.entity.Titulo;
import com.strathub2.backend2.repository.TituloRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TituloService {

        private final TituloRepository tituloRepository;

        public TituloService(TituloRepository tituloRepository) {
            this.tituloRepository = tituloRepository;
        }

        @Transactional(readOnly = true)
        public TituloDTO findById(Long id){
            Optional<Titulo> tituloOptional = tituloRepository.findById(id);
            if (tituloOptional.isEmpty()) {
                throw new RuntimeException("Time não encontrado com o ID: " + id);
            }
            return new TituloDTO(tituloOptional.get());
        }

        @Transactional
        public TituloDTO save(TituloDTO tituloDTO) {
            Titulo titulo = new Titulo();
            BeanUtils.copyProperties(tituloDTO, titulo);
            Titulo saved = tituloRepository.save(titulo);
            return new TituloDTO(saved);
        }
}
