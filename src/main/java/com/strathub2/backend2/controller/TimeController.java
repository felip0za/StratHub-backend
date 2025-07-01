package com.strathub2.backend2.controller;

import com.strathub2.backend2.dto.TimeDTO;
import com.strathub2.backend2.service.TimeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/times")
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping
    public TimeDTO salvar(@RequestBody TimeDTO timeDTO) {
        return timeService.save(timeDTO);
    }

    @GetMapping("{id}")
    public TimeDTO findById(@PathVariable Long id) {
        return timeService.findById(id);
    }

    @PutMapping("{nome}")
    public TimeDTO atualizar(@PathVariable String nome, @RequestBody TimeDTO timeDTO) {
        return timeService.updateByName(nome, timeDTO);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        timeService.deleteById(id);
    }

}
