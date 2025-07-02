package com.strathub2.backend2.dto;

import com.strathub2.backend2.entity.Time;

public class TimeDTO {
    private Integer id;
    private String nome;
    private String descricao;
    private String imagemBase64;

    public TimeDTO() {
    }

    public TimeDTO(Time time) {
        this.id = time.getId();
        this.nome = time.getNome();
        this.descricao = time.getDescricao();

        if (time.getImagemBase64() != null && !time.getImagemBase64().isEmpty()) {
            // ✅ Adiciona o prefixo para o React conseguir exibir a imagem direto
            this.imagemBase64 = "data:image/png;base64," + time.getImagemBase64();
        } else {
            this.imagemBase64 = null;
        }
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
}
