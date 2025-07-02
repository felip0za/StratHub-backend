package com.strathub2.backend2.controller;

import com.strathub2.backend2.entity.Usuario;
import com.strathub2.backend2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repo;

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestBody Usuario usuario) {
        if (repo.findByEmail(usuario.getEmail()).isPresent()) {
            return "Email já cadastrado!";
        }
        repo.save(usuario);
        return "Usuário cadastrado com sucesso!";
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        return repo.findByEmail(usuario.getEmail())
                .filter(u -> u.getSenha().equals(usuario.getSenha()))
                .map(u -> "Login bem-sucedido!")
                .orElse("Email ou senha inválidos.");
    }
}
