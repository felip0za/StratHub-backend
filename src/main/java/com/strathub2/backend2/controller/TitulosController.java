package com.strathub2.backend2.controller;
import com.strathub2.backend2.dto.TituloDTO;
import com.strathub2.backend2.service.TituloService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/titulos")
public class TitulosController {

    private final TituloService tituloService;

    public TitulosController(TituloService tituloService) {
        this.tituloService = tituloService;
    }

    @PostMapping
    public TituloDTO salvar(@RequestBody TituloDTO tituloDTO) {
        return tituloService.save(tituloDTO);
    }

    @GetMapping("{id}")
    public TituloDTO findById(@PathVariable Long id) {
        return tituloService.findById(id);
    }

    @PutMapping("{id}")
    public TituloDTO atualizar(@PathVariable Long id, @RequestBody TituloDTO tituloDTO) {
        return tituloService.save(tituloDTO);
    }
}
