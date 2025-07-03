package com.strathub2.backend2.dto;

import com.strathub2.backend2.entity.Usuario;

public class UsuarioDTO {
    private Integer id;
    private String email;
    private String senha;
    private String imagem_usuario;
    private String nome;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        if (usuario != null) {
            this.id = usuario.getId();
            this.email = usuario.getEmail();
            this.nome = usuario.getNome();
            this.senha = usuario.getSenha(); // Inclua, se necessário no contexto do DTO
            if (usuario.getImagem_usuario() != null && !usuario.getImagem_usuario().isEmpty()) {
                // ✅ Adiciona o prefixo para o React conseguir exibir a imagem direto
                this.imagem_usuario = "data:image/png;base64," + usuario.getImagem_usuario();
            } else {
                this.imagem_usuario = null;
            }
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getImagem_usuario() {
        return imagem_usuario;
    }

    public void setImagem_usuario(String imagem_usuario) {
        this.imagem_usuario = imagem_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
