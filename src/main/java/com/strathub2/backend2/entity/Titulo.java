package com.strathub2.backend2.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "Titulos")
public class Titulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_titulo")
    private Integer id;

    @Column(name = "nm_titulo")
    private String nome;

    @Column(name = "te_descricao_titulo")
    private String descricao;

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

    @Override
    public String toString() {
        return "Titulo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
