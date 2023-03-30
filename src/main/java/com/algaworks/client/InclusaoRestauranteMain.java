package com.algaworks.client;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import com.algaworks.client.api.ClientApiException;
import com.algaworks.client.api.RestauranteClient;
import com.algaworks.client.model.RestauranteModel;
import com.algaworks.client.model.input.CidadeIdInput;
import com.algaworks.client.model.input.CozinhaIdInput;
import com.algaworks.client.model.input.EnderecoInput;
import com.algaworks.client.model.input.RestauranteInput;

public class InclusaoRestauranteMain {
    
    public static void main(String[] args) {
        
        try {
			RestTemplate restTemplate = new RestTemplate();
			
			RestauranteClient restauranteClient = new RestauranteClient(
					restTemplate, "http://api.algafood.local:8080");
			
			CozinhaIdInput cozinha = new CozinhaIdInput();
			cozinha.setId(1L);
			
			CidadeIdInput cidade = new CidadeIdInput();
			cidade.setId(1L);

			EnderecoInput endereco = new EnderecoInput();
			endereco.setCep("63560-000");
			endereco.setLogradouro("Rua Joaquim");
			endereco.setNumero("146");
			endereco.setComplemento("Apto");
			endereco.setBairro("Nova Acopiara");
			endereco.setCidade(cidade);

			RestauranteInput restaurante = new RestauranteInput();
			restaurante.setNome("");
			restaurante.setTaxaFrete(new BigDecimal("10.00"));
			restaurante.setCozinha(cozinha);
			restaurante.setEndereco(endereco);
			
			RestauranteModel restauranteModel = restauranteClient.adicionar(restaurante);

			System.out.println(restauranteModel);
		} catch (ClientApiException e) {
			
			if (e.getProblem() != null) {
				System.out.println(e.getProblem().getUserMessage());
				
				e.getProblem().getObjects().stream()
				  .forEach(p -> System.out.println("- " + p.getUserMessage()));
				
			  } else {
				System.out.println("Erro desconhecido");
				e.printStackTrace();
			  }
		}
    }
}
