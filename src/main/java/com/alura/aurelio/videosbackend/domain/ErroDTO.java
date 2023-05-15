package com.alura.aurelio.videosbackend.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroDTO {
	
	private List<String> mensagem;

}
