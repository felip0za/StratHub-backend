package com.strathub2.backend2.dto;

import com.strathub2.backend2.entity.Time;
import jakarta.persistence.*;

public class TimeDTO {
    private Integer id;

    private String nome;

    private String descricao;

    public TimeDTO() {

    }

    public TimeDTO(Time time){
        this.id = time.getId();
        this.nome = time.getNome();
        this.descricao = time.getDescricao();
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
}
