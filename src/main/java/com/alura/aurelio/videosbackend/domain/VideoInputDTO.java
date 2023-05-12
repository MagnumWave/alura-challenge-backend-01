package com.alura.aurelio.videosbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoInputDTO {
	
	
	private String titulo;
	
	
	private String descricao;
	
	
	private String url;
	
	public Video toVideo() throws Exception {
		
		if(this.titulo.equals(null)
			|| this.titulo.isBlank()
			|| this.titulo.isEmpty()) {
				throw new Exception("blop");
		}
		
		return new Video(null ,this.titulo, this.descricao, this.url);
	}
	
	//TODO: implementar validaTitulo e validaUrl
	
}
