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
public class VideoInputDTO {
	
	private String titulo;
	
	private String descricao;
	
	private String url;
	
	public Video toVideo() throws CustomException  {
		validaDTO();
		return new Video(null, this.titulo, this.descricao, this.url);
	}

	private void validaDTO() {
		List<String> erros = new ArrayList<>();
	
		if(StringUtils.isEmpty(getTitulo()) || StringUtils.isBlank(getTitulo())) {
			erros.add("Título do vídeo não pode ser nulo ou vazio.");
		}
		
		if(getTitulo().length() > 70) {
			erros.add("Título do vídeo não pode ter mais do que 70 caracteres.");
		}
		
		if(StringUtils.isEmpty(getDescricao()) || StringUtils.isBlank(getDescricao())) {
			erros.add("Descrição do vídeo não pode ser nula ou vazia.");
		}
		
		if(getDescricao().length() > 250) {
			erros.add("Descrição do vídeo não pode ter mais do que 250 caracteres.");
		}
		
		if(StringUtils.isEmpty(getUrl()) || StringUtils.isBlank(getUrl())) {
			erros.add("Url do vídeo não pode ser nula ou vazia.");
		}
		
		if(getUrl().length() > 70) {
			erros.add("Url do vídeo não pode ter mais do que 70 caracteres.");
		}
		
		if(!erros.isEmpty()) {
			throw new CustomException("Esta requisição apresentou os seguintes erros: ", erros);
		}
	}
	
}
