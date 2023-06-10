package com.alura.aurelio.videosbackend.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.alura.aurelio.videosbackend.domain.CategoriaInputDTO;
import com.alura.aurelio.videosbackend.repository.CategoriaRepository;

class CategoriaServiceImplTest {
	
	@Mock
	private CategoriaRepository repository;
	
	@Mock
	private CategoriaServiceImpl service;
	
	@Captor
	private ArgumentCaptor<CategoriaInputDTO> captor;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.service = new CategoriaServiceImpl(repository);
	}
	
	@Test
	void naoDeveriaCadastrarCategoriaSemTitulo() {
		String tituloVazio1 = "";
		String tituloVazio2 = " ";
		String tituloVazio3 = null;
		
		CategoriaInputDTO dto1 = new CategoriaInputDTO(tituloVazio1,"rgba(0,0,0,0.5)");
		CategoriaInputDTO dto2 = new CategoriaInputDTO(tituloVazio2,"rgba(0,0,0,0.5)");
		CategoriaInputDTO dto3 = new CategoriaInputDTO(tituloVazio3,"rgba(0,0,0,0.5)");
				
		assertFalse(service.errosDoDTO(dto1).isEmpty());
		assertFalse(service.errosDoDTO(dto2).isEmpty());
		assertFalse(service.errosDoDTO(dto3).isEmpty());
	}
	
	@Test
	void naoDeveriaCadastrarCategoriaSemCor() {
		String corVazia1 = "";
		String corVazia2 = " ";
		String corVazia3 = null;
		
		CategoriaInputDTO dto1 = new CategoriaInputDTO("titulo",corVazia1);
		CategoriaInputDTO dto2 = new CategoriaInputDTO("titulo",corVazia2);
		CategoriaInputDTO dto3 = new CategoriaInputDTO("titulo",corVazia3);
				
		assertFalse(service.errosDoDTO(dto1).isEmpty());
		assertFalse(service.errosDoDTO(dto2).isEmpty());
		assertFalse(service.errosDoDTO(dto3).isEmpty());
	}
	
	@Test
	void naoDeveriaAceitarIdInexistente() {
		Long id = service.obterUltimoID();
		assertFalse(service.idIsValid(id+1l));
	}

}
