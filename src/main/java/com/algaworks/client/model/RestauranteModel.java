package com.algaworks.client.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RestauranteModel {
    
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaModel cozinha;
    private boolean ativo;
    private boolean aberto;
    private EnderecoModel endereco;
}
