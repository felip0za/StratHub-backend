package com.strathub2.backend2.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "partidas")
public class Partidas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partida")
    private Integer id;

    @Column(name = "nm_partida")
    private String nome;

    @Column(name = "te_descricao_partida")
    private String descricao;

    @Column(name = "tp_partida")
    private String tipo_partida;

    @Column(name = "nu_valor")
    private BigDecimal valor;

    @Column(name = "qt_jogadores")
    private Integer jogadores;

    public Partidas() {

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

    @Override
    public String toString() {
        return "Partidas{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tipo_partida='" + tipo_partida + '\'' +
                ", valor=" + valor +
                ", jogadores=" + jogadores +
                '}';
    }
}