package com.alura.aurelio.videosbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoOutputDTO {
	
	Long id;
	
	private String titulo;
	
	private String descricao;
	
	private String url;
	
	Long idCategoria;
	
}
