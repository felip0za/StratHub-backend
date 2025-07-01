package com.strathub2.backend2.dto;

import com.strathub2.backend2.entity.Partidas;

import java.math.BigDecimal;

public class PartidasDTO {
    private Integer id;
    private String nome;
    private String descricao;
    private String tipo_partida;
    private BigDecimal valor;
    private Integer jogadores;

    // Construtor que recebe a entidade e preenche o DTO corretamente
    public PartidasDTO(Partidas partidas) {
        this.id = partidas.getId();
        this.nome = partidas.getNome();
        this.descricao = partidas.getDescricao();
        this.tipo_partida = partidas.getTipo_partida();
        this.valor = partidas.getValor();
        this.jogadores = partidas.getJogadores();
    }

    public PartidasDTO() {
    }

    // Getters e Setters

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

    public String getTipo_partida() {
        return tipo_partida;
    }

    public void setTipo_partida(String tipo_partida) {
        this.tipo_partida = tipo_partida;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getJogadores() {
        return jogadores;
    }

    public void setJogadores(Integer jogadores) {
        this.jogadores = jogadores;
    }
}
