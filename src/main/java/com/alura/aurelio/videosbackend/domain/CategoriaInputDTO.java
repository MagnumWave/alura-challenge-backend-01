package com.alura.aurelio.videosbackend.domain;



//import org.springframework.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaInputDTO {
	
	private String titulo;
	
	private String cor;
	
}
