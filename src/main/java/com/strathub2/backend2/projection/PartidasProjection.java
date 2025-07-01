package com.strathub2.backend2.projection;

import java.math.BigDecimal;

public interface PartidasProjection {
    Long getId();
    String getNome();
    String getDescricao();
    String getTipo_partida();
    BigDecimal getValor();
    Integer getJogadores();
}
