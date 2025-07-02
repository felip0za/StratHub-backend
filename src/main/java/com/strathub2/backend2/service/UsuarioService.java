package com.strathub2.backend2.service;

import com.strathub2.backend2.entity.Usuario;
import com.strathub2.backend2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public String cadastrar(Usuario usuario) {
        Optional<Usuario> existente = repo.findByEmail(usuario.getEmail());

        if (existente.isPresent()) {
            return "Email já cadastrado!";
        }

        repo.save(usuario);
        return "Usuário cadastrado com sucesso!";
    }

    public String login(String email, String senha) {
        Optional<Usuario> usuario = repo.findByEmail(email);

        if (usuario.isPresent() && usuario.get().getSenha().equals(senha)) {
            return "Login bem-sucedido!";
        } else {
            return "Email ou senha inválidos.";
        }
    }
}
