package com.strathub2.backend2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "te_email")
    private String email;

    @Column(name = "te_senha")
    private String senha;

    @Column(name = "te_imagem_usuario", columnDefinition = "TEXT")
    private String imagem_usuario;

    @Column(name = "te_nome")
    private String nome;

    public Usuario() {
    }

    public Usuario(Integer id, String email, String senha, String nome) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }
    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getImagem_usuario() {
        return imagem_usuario;
    }

    public void setImagem_usuario(String imagem_usuario) {
        this.imagem_usuario = imagem_usuario;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", imagem_usuario='" + (imagem_usuario != null ? "[BASE64]" : "null") + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
