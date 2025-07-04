package com.strathub2.backend2.service;

import com.strathub2.backend2.dto.UsuarioDTO;
import com.strathub2.backend2.dto.TimeDTO;
import com.strathub2.backend2.entity.Time;
import com.strathub2.backend2.entity.Usuario;
import com.strathub2.backend2.repository.TimeRepository;
import com.strathub2.backend2.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TimeRepository timeRepository;

    // Método para buscar usuário por ID
    public UsuarioDTO findById(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(UsuarioDTO::new).orElse(null);
    }

    // Novo método para retornar a entidade Usuario diretamente (para o controller usar)
    public Usuario findEntityById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Transactional
    public String cadastrar(UsuarioDTO dto) {
        Optional<Usuario> existente = usuarioRepository.findByEmail(dto.getEmail());

        if (existente.isPresent()) {
            return "Email já cadastrado!";
        }

        if (dto.getTimeId() != null && !timeRepository.existsById(dto.getTimeId())) {
            throw new EntityNotFoundException("Time com ID " + dto.getTimeId() + " não existe.");
        }

        Usuario novoUsuario = new Usuario();
        copyDtoToEntity(dto, novoUsuario);

        usuarioRepository.save(novoUsuario);
        return "Usuário cadastrado com sucesso!";
    }

    @Transactional
    public String login(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isPresent() && senha != null && senha.equals(usuario.get().getSenha())) {
            return "Login bem-sucedido!";
        } else {
            return "Email ou senha inválidos.";
        }
    }

    @Transactional
    public UsuarioDTO updateById(Integer id, UsuarioDTO usuarioDTO) {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não foi encontrado com o ID: " + id));

        if (usuarioDTO.getTimeId() != null && !timeRepository.existsById(usuarioDTO.getTimeId())) {
            throw new EntityNotFoundException("Time com ID " + usuarioDTO.getTimeId() + " não existe.");
        }

        copyDtoToEntity(usuarioDTO, existingUsuario);
        Usuario updatedUsuario = usuarioRepository.save(existingUsuario);
        return new UsuarioDTO(updatedUsuario);
    }

    @Transactional
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioDTO::new)
                .toList();
    }

    /**
     * Método auxiliar para copiar os dados do DTO para a entidade Usuario.
     */
    private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setImagem_usuario(dto.getImagem_usuario());

        if (dto.getTimeId() != null) {
            Time time = timeRepository.findById(dto.getTimeId())
                    .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com ID: " + dto.getTimeId()));
            entity.setTime(time);
        } else {
            entity.setTime(null);
        }
    }
}
