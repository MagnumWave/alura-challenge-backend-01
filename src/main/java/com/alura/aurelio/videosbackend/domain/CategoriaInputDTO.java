package com.alura.aurelio.videosbackend.domain;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.util.StringUtils;

import com.alura.aurelio.videosbackend.infra.CustomException;

import lombok.AllArgsConstructor;
import io.micrometer.common.util.StringUtils;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaInputDTO {
	
	private String titulo;
	
	private String cor;
	
	public Categoria toCategoria() throws CustomException  {
		validaDTO();
		return new Categoria(null, this.titulo, this.cor);
	}

	private void validaDTO() {
		List<String> erros = new ArrayList<>();
	
		if(StringUtils.isEmpty(getTitulo()) || StringUtils.isBlank(getTitulo())) {
			erros.add("Título da categoria não pode ser nulo ou vazio.");
		}
		
		if(getTitulo().length() > 30) {
			erros.add("Título da categoria não pode ter mais do que 30 caracteres.");
		}
		
		if(StringUtils.isEmpty(getCor()) || StringUtils.isBlank(getCor())) {
			erros.add("Cor da categoria não pode ser nula ou vazia.");
		}
		
		if(getCor().length() > 30) {
			erros.add("Cor da categoria não pode ter mais do que 30 caracteres.");
		}
		
		if(!erros.isEmpty()) {
			throw new CustomException("Esta requisição apresentou os seguintes erros: ", erros);
		}
	}
	
}
