package com.strathub2.backend2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "times")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_time")
    private Integer id;

    @Column(name = "nm_time")
    private String nome;

    @Column(name = "te_descricao")
    private String descricao;

    @Lob
    @Column(name = "te_imagem_base64", columnDefinition = "TEXT")
    private String imagemBase64;

    public Time() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemBase64() {
        return imagemBase64;
    }

    public void setImagemBase64(String imagemBase64) {
        this.imagemBase64 = imagemBase64;
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", imagemBase64='" + (imagemBase64 != null ? "[BASE64]" : "null") + '\'' +
                '}';
    }
}
