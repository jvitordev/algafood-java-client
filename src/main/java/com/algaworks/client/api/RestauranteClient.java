package com.algaworks.client.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.algaworks.client.model.RestauranteModel;
import com.algaworks.client.model.RestauranteResumoModel;
import com.algaworks.client.model.input.RestauranteInput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteClient {
    
    private static final String RESOURCE_PATH = "/restaurantes";
	
	private RestTemplate restTemplate;
	private String url;
	
	public List<RestauranteResumoModel> listar() {
		try {
			URI resourceUri = URI.create(url + RESOURCE_PATH);
			
			RestauranteResumoModel[] restaurantes = restTemplate
					.getForObject(resourceUri, RestauranteResumoModel[].class);
			
			return Arrays.asList(restaurantes);
		} catch (RestClientResponseException e) {

			throw new ClientApiException(e.getMessage(), e);
		}
	}

	public RestauranteModel adicionar(RestauranteInput restauranteInput) {
		
		try {
			URI resourceUri = URI.create(url + RESOURCE_PATH);
	
			RestauranteModel restaurante = restTemplate
				.postForObject(resourceUri, restauranteInput, RestauranteModel.class);
	
			return restaurante;
		} catch (RestClientResponseException e) {
			
			throw new ClientApiException(e.getMessage(), e);
		}
	}
}
