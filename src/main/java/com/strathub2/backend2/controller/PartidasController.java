package com.strathub2.backend2.controller;

import com.strathub2.backend2.dto.PartidasDTO;
import com.strathub2.backend2.service.PartidasService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/partidas")
@CrossOrigin(origins = "http://localhost:8080") // Permite acesso do frontend (React) no localhost:8080
public class PartidasController {

    private final PartidasService partidasService;

    public PartidasController(PartidasService partidasService) {
        this.partidasService = partidasService;
    }

    @PostMapping // Criar partida
    public PartidasDTO salvar(@RequestBody PartidasDTO partidasDTO) {
        return partidasService.save(partidasDTO);
    }

    @GetMapping("{id}") // Achar partida por id
    public PartidasDTO findById(@PathVariable Long id) {
        return partidasService.findById(id);
    }

    @GetMapping("/nome/{nome}") // Achar partida por nome
    public PartidasDTO findByNome(@PathVariable String nome) {
        return partidasService.findByNome(nome);
    }

}
