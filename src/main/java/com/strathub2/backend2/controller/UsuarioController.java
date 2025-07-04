package com.strathub2.backend2.controller;

import com.strathub2.backend2.dto.TimeDTO;
import com.strathub2.backend2.dto.UsuarioDTO;
import com.strathub2.backend2.entity.Time;
import com.strathub2.backend2.entity.Usuario;
import com.strathub2.backend2.service.TimeService;
import com.strathub2.backend2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private TimeService timeService; // Service para buscar o time

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            String resultado = service.cadastrar(usuarioDTO);
            if (resultado.contains("já cadastrado")) {
                return ResponseEntity.badRequest().body(resultado);
            }
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno ao cadastrar: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        try {
            if (usuario.getEmail() == null || usuario.getSenha() == null) {
                return ResponseEntity.badRequest().body("Email e senha são obrigatórios.");
            }

            String resultado = service.login(usuario.getEmail(), usuario.getSenha());

            if (resultado.equals("Login bem-sucedido!")) {
                return ResponseEntity.ok(resultado);
            } else {
                return ResponseEntity.status(401).body(resultado);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno ao fazer login: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            UsuarioDTO usuario = service.findById(id);
            if (usuario == null) {
                return ResponseEntity.status(404).body("Usuário não encontrado");
            }
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        try {
            List<UsuarioDTO> usuarios = service.findAll();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO atualizado = service.updateById(id, usuarioDTO);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Usuário não encontrado com o ID: " + id);
        }
    }

    // Endpoint para retornar o time do usuário
    @GetMapping("/meu-time/{id}")
    public ResponseEntity<?> obterMeuTime(@PathVariable Integer id) {
        try {
            // Busca o usuário pelo ID
            UsuarioDTO usuario = service.findById(id);
            if (usuario == null) {
                return ResponseEntity.status(404).body("Usuário não encontrado");
            }

            // Verifica se o usuário tem um time associado
            if (usuario.getTime() == null) {
                return ResponseEntity.status(404).body("Usuário sem time");
            }

            // Busca o time associado ao usuário
            Time time = usuario.getTime();
            TimeDTO timeDTO = new TimeDTO();
            timeDTO.setNome(time.getNome());
            timeDTO.setDescricao(time.getDescricao());
            timeDTO.setImagemBase64(time.getImagemBase64());

            return ResponseEntity.ok(timeDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar o time: " + e.getMessage());
        }
    }
}
