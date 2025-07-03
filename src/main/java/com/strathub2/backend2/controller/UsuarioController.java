package com.strathub2.backend2.controller;

import com.strathub2.backend2.dto.TimeDTO;
import com.strathub2.backend2.dto.UsuarioDTO;
import com.strathub2.backend2.entity.Usuario;
import com.strathub2.backend2.repository.UsuarioRepository;
import com.strathub2.backend2.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
        try {
            if (repo.findByEmail(usuario.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("Email já cadastrado!");
            }

            Usuario novoUsuario = repo.save(usuario);
            return ResponseEntity.ok(novoUsuario);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno ao cadastrar: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        return repo.findByEmail(usuario.getEmail())
                .filter(u -> u.getSenha().equals(usuario.getSenha()))
                .map(u -> ResponseEntity.ok("Login bem-sucedido!"))
                .orElse(ResponseEntity.status(401).body("Email ou senha inválidos."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            UsuarioDTO usuario = service.findById(id);
            if (usuario == null) {
                return ResponseEntity.badRequest().body("Usuário não encontrado");
            }
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro interno: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO atualizado = service.updateById(id, usuarioDTO);
            return ResponseEntity.ok(atualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Usuário não encontrado com o ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar o usuário: " + e.getMessage());
        }
    }
}
