package com.strathub2.backend2.service;

import com.strathub2.backend2.dto.TimeDTO;
import com.strathub2.backend2.dto.UsuarioDTO;
import com.strathub2.backend2.entity.Time;
import com.strathub2.backend2.entity.Usuario;
import com.strathub2.backend2.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO findById(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(UsuarioDTO::new).orElse(null);
    }

    @Transactional
    public String cadastrar(Usuario usuario) {
        Optional<Usuario> existente = usuarioRepository.findByEmail(usuario.getEmail());

        if (existente.isPresent()) {
            return "Email já cadastrado!";
        }

        usuarioRepository.save(usuario);
        return "Usuário cadastrado com sucesso!";
    }

    @Transactional
    public String login(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isPresent() && usuario.get().getSenha().equals(senha)) {
            return "Login bem-sucedido!";
        } else {
            return "Email ou senha inválidos.";
        }
    }

    @Transactional
    public UsuarioDTO updateById(Integer id, UsuarioDTO usuarioDTO) {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não foi encontrado com o ID: " + id));

        copyDtoToEntity(usuarioDTO, existingUsuario);
        Usuario updatedUsuario = usuarioRepository.save(existingUsuario);
        return new UsuarioDTO(updatedUsuario);
    }

    /**
     * Método auxiliar para copiar os dados do DTO para a entidade Time.
     */
    private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setImagem_usuario(dto.getImagem_usuario());

    }
}
