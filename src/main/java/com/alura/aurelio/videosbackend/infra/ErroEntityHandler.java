package com.alura.aurelio.videosbackend.infra;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alura.aurelio.videosbackend.domain.ErroDTO;

@ControllerAdvice
public class ErroEntityHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(value = CustomException.class)
	protected ResponseEntity<ErroDTO> handleConflict(CustomException ex) {
			
			List<String> listaDeErros = new ArrayList<>(); 
			listaDeErros.add(ex.getMessage());
			if(ex.hasExtraMessage()) {
				listaDeErros.addAll(ex.getExtraMessage());
			}
			ErroDTO erro = new ErroDTO(listaDeErros);
			ResponseEntity<ErroDTO> response = new ResponseEntity<ErroDTO>(erro, HttpStatus.BAD_REQUEST);
	        return response;
	    }
}
