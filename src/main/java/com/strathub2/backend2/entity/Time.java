package com.strathub2.backend2.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "times")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_time")
    private Integer id;

    @Column(name="nm_time")
    private String nome;

    @Column(name="te_descricao")
    private String descricao;

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


}
