package com.alura.aurelio.videosbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoOutputDTO {
	
	private Long id;
	
	private String titulo;
	
	private String descricao;
	
	private String url;
	
	private Long idCategoria;
	
}
