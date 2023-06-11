package com.alura.aurelio.videosbackend.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.alura.aurelio.videosbackend.domain.CategoriaInputDTO;
import com.alura.aurelio.videosbackend.repository.CategoriaRepository;

class CategoriaServiceImplTest {
	
	@Mock
	private CategoriaRepository repository;
	
	@Mock
	private CategoriaServiceImpl service;

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
		String tituloNormal = "Título De Um Video Normal";
		
		CategoriaInputDTO dto1 = new CategoriaInputDTO(tituloVazio1,"rgba(0,0,0,0.5)");
		CategoriaInputDTO dto2 = new CategoriaInputDTO(tituloVazio2,"rgba(0,0,0,0.5)");
		CategoriaInputDTO dto3 = new CategoriaInputDTO(tituloVazio3,"rgba(0,0,0,0.5)");
		CategoriaInputDTO dto4 = new CategoriaInputDTO(tituloNormal,"rgba(0,0,0,0.5)");
				
		assertFalse(service.errosDoDTO(dto1).isEmpty());
		assertFalse(service.errosDoDTO(dto2).isEmpty());
		assertFalse(service.errosDoDTO(dto3).isEmpty());
		assertTrue(service.errosDoDTO(dto4).isEmpty());
	}
	
	@Test
	void naoDeveriaCadastrarCategoriaSemCor() {
		String corVazia1 = "";
		String corVazia2 = " ";
		String corVazia3 = null;
		String corNormal = "rgba(0,0,0,0.5)";
		
		CategoriaInputDTO dto1 = new CategoriaInputDTO("titulo",corVazia1);
		CategoriaInputDTO dto2 = new CategoriaInputDTO("titulo",corVazia2);
		CategoriaInputDTO dto3 = new CategoriaInputDTO("titulo",corVazia3);
		CategoriaInputDTO dto4 = new CategoriaInputDTO("titulo",corNormal);
				
		assertFalse(service.errosDoDTO(dto1).isEmpty());
		assertFalse(service.errosDoDTO(dto2).isEmpty());
		assertFalse(service.errosDoDTO(dto3).isEmpty());
		assertTrue(service.errosDoDTO(dto4).isEmpty());
	}
	
	@Test
	void naoDeveriaCadastrarTituloComMaisDoQue30Caracteres() {
		String tituloAbaixoDe30 = "123456789012345678901234567";
		String tituloCom30 = "123456789012345678901234567890";
		String tituloAcimaDe30 = "123456789012345678901234567890abdc";
		
		CategoriaInputDTO dto1 = new CategoriaInputDTO(tituloAbaixoDe30,"rgba(0,0,0,0.5)");
		CategoriaInputDTO dto2 = new CategoriaInputDTO(tituloCom30,"rgba(0,0,0,0.5)");
		CategoriaInputDTO dto3 = new CategoriaInputDTO(tituloAcimaDe30,"rgba(0,0,0,0.5)");
				
		assertTrue(service.errosDoDTO(dto1).isEmpty());
		assertTrue(service.errosDoDTO(dto2).isEmpty());
		assertFalse(service.errosDoDTO(dto3).isEmpty());
	}
	
	@Test
	void naoDeveriaCadastrarCorComMaisDoQue30Caracteres() {
		String corAbaixoDe30 = "123456789012345678901234567";
		String corCom30 = "123456789012345678901234567890";
		String corAcimaDe30 = "123456789012345678901234567890abdc";
		
		CategoriaInputDTO dto1 = new CategoriaInputDTO("titulo",corAbaixoDe30);
		CategoriaInputDTO dto2 = new CategoriaInputDTO("titulo",corCom30);
		CategoriaInputDTO dto3 = new CategoriaInputDTO("titulo",corAcimaDe30);
				
		assertTrue(service.errosDoDTO(dto1).isEmpty());
		assertTrue(service.errosDoDTO(dto2).isEmpty());
		assertFalse(service.errosDoDTO(dto3).isEmpty());
	}
	
	@Test
	void naoDeveriaAceitarIdInexistente() {
		Mockito.when(repository.existsById(1L)).thenReturn(true);
		Mockito.when(repository.existsById(null)).thenThrow(IllegalArgumentException.class);
		
		long randomInt = Math.round(Math.random()*100);
		long idValido = 1L;
		long idInvalido = 0L;
		
		if (randomInt == 1L) {
			idInvalido = randomInt + Math.round(Math.random()*100);
		} else {
			idInvalido = randomInt;
		}
		
		assertTrue(service.idIsValid(idValido));
		assertFalse(service.idIsValid(idInvalido));
		assertThrows(IllegalArgumentException.class, () -> service.idIsValid(null));
	}

}
