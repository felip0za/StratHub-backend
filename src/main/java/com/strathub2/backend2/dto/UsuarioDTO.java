package com.strathub2.backend2.dto;

import com.strathub2.backend2.entity.Time;
import com.strathub2.backend2.entity.Usuario;

public class UsuarioDTO {
    private Integer id;
    private String email;
    private String nome;
    private String senha;
    private String imagem_usuario;

    private Integer timeId;       // ✅ Corrigido de Long para Integer
    private String nomeTime;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        if (usuario != null) {
            this.id = usuario.getId();
            this.email = usuario.getEmail();
            this.nome = usuario.getNome();
            this.senha = usuario.getSenha();

            if (usuario.getImagem_usuario() != null && !usuario.getImagem_usuario().isEmpty()) {
                this.imagem_usuario = "data:image/png;base64," + usuario.getImagem_usuario();
            }

            Time time = usuario.getTime();
            if (time != null) {
                this.timeId = time.getId();      // ✅ Inteiro
                this.nomeTime = time.getNome();
            }
        }
    }

    // Getters e setters

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem_usuario() {
        return imagem_usuario;
    }

    public void setImagem_usuario(String imagem_usuario) {
        this.imagem_usuario = imagem_usuario;
    }

    public Integer getTimeId() {                      // ✅ Corrigido
        return timeId;
    }

    public void setTimeId(Integer timeId) {           // ✅ Corrigido
        this.timeId = timeId;
    }

    public String getNomeTime() {
        return nomeTime;
    }

    public void setNomeTime(String nomeTime) {
        this.nomeTime = nomeTime;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
