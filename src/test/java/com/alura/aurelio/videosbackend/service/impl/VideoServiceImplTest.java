package com.alura.aurelio.videosbackend.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.alura.aurelio.videosbackend.domain.VideoInputDTO;
import com.alura.aurelio.videosbackend.repository.CategoriaRepository;
import com.alura.aurelio.videosbackend.repository.VideoRepository;

class VideoServiceImplTest {
	
	@Mock
	CategoriaRepository categoriaRepository;
	
	@Mock
	VideoRepository repository;
	
	@Mock
	VideoServiceImpl service;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.service = new VideoServiceImpl(repository, categoriaRepository);
	}
	
	@Test
	void naoDeveriaCadastrarVideoSemTitulo() {
		String tituloVazio1 = "";
		String tituloVazio2 = " ";
		String tituloVazio3 = null;
		String tituloNormal = "Título do Video Normal";
		
		VideoInputDTO dto1 = new VideoInputDTO(tituloVazio1,"era uma vez um video", "url.do.video.com", 1L);
		VideoInputDTO dto2 = new VideoInputDTO(tituloVazio2,"era uma vez um video", "url.do.video.com", 1L);
		VideoInputDTO dto3 = new VideoInputDTO(tituloVazio3,"era uma vez um video", "url.do.video.com", 1L);
		VideoInputDTO dto4 = new VideoInputDTO(tituloNormal,"era uma vez um video", "url.do.video.com", 1L);

				
		assertFalse(service.errosDoDTO(dto1).isEmpty());
		assertFalse(service.errosDoDTO(dto2).isEmpty());
		assertFalse(service.errosDoDTO(dto3).isEmpty());
		assertTrue(service.errosDoDTO(dto4).isEmpty());
	}
	
	@Test
	void naoDeveriaCadastrarTituloComMaisDe70Caracteres() {
		String tituloComMenosDe70 = "12345678901234567890123456789012345678901234567890";
		String tituloCom70Chars = "1234567890123456789012345678901234567890123456789012345678901234567890";
		String tituloComMaisDe70 = "1234567890123456789012345678901234567890123456789012345678901234567890agffg";
		
		
		VideoInputDTO dto1 = new VideoInputDTO(tituloComMenosDe70,"era uma vez um video", "url.do.video.com", 1L);
		VideoInputDTO dto2 = new VideoInputDTO(tituloCom70Chars,"era uma vez um video", "url.do.video.com", 1L);
		VideoInputDTO dto3 = new VideoInputDTO(tituloComMaisDe70,"era uma vez um video", "url.do.video.com", 1L);
				
		assertTrue(service.errosDoDTO(dto1).isEmpty());
		assertTrue(service.errosDoDTO(dto2).isEmpty());
		assertFalse(service.errosDoDTO(dto3).isEmpty());
	}
	
	@Test
	void naoDeveriaCadastrarVideoSemDescricao() {
		String descricaoVazia1 = "";
		String descricaoVazia2 = " ";
		String descricaoVazia3 = null;
		String descricaoNormal = "Este video fala sobre como uma descrição pode ser normal";
		
		VideoInputDTO dto1 = new VideoInputDTO("Titulo do Video 1", descricaoVazia1, "www.site.com", 1L);
		VideoInputDTO dto2 = new VideoInputDTO("Titulo do Video 2", descricaoVazia2, "www.site.com", 1L);
		VideoInputDTO dto3 = new VideoInputDTO("Titulo do Video 3", descricaoVazia3, "www.site.com", 1L);
		VideoInputDTO dto4 = new VideoInputDTO("Titulo do Video 3", descricaoNormal, "www.site.com", 1L);
				
		assertFalse(service.errosDoDTO(dto1).isEmpty());
		assertFalse(service.errosDoDTO(dto2).isEmpty());
		assertFalse(service.errosDoDTO(dto3).isEmpty());
		assertTrue(service.errosDoDTO(dto4).isEmpty());
	}
	
	@Test
	void naoDeveriaCadastrarDescricaoComMaisDe250Caracteres() {
		String descricaoComMenosDe250Chars = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345";
		String descricaoCom250Chars = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
		String descricaoComMaisDe250Chars = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890aaaaa";
		
		
		VideoInputDTO dto1 = new VideoInputDTO("Titulo do Video 1", descricaoComMenosDe250Chars, "url.do.video.com", 1L);
		VideoInputDTO dto2 = new VideoInputDTO("Titulo do Video 2", descricaoCom250Chars, "url.do.video.com", 1L);
		VideoInputDTO dto3 = new VideoInputDTO("Titulo do Video 3", descricaoComMaisDe250Chars, "url.do.video.com", 1L);
				
		assertTrue(service.errosDoDTO(dto1).isEmpty());
		assertTrue(service.errosDoDTO(dto2).isEmpty());
		assertFalse(service.errosDoDTO(dto3).isEmpty());
	}
	
	@Test
	void naoDeveriaCadastrarVideoSemURL() {
		String urlVazia1 = "";
		String urlVazia2 = " ";
		String urlVazia3 = null;
		String urlNormal = "www.sites.com.br";
		
		VideoInputDTO dto1 = new VideoInputDTO("Titulo do Video 1", "era uma vez um video", urlVazia1, 1L);
		VideoInputDTO dto2 = new VideoInputDTO("Titulo do Video 2", "era uma vez um video", urlVazia2, 1L);
		VideoInputDTO dto3 = new VideoInputDTO("Titulo do Video 3", "era uma vez um video", urlVazia3, 1L);
		VideoInputDTO dto4 = new VideoInputDTO("Titulo do Video 3", "era uma vez um video", urlNormal, 1L);
				
		assertFalse(service.errosDoDTO(dto1).isEmpty());
		assertFalse(service.errosDoDTO(dto2).isEmpty());
		assertFalse(service.errosDoDTO(dto3).isEmpty());
		assertTrue(service.errosDoDTO(dto4).isEmpty());
	}
	
	@Test
	void naoDeveriaCadastrarURLComMaisDe70Caracteres() {
		String urlComMenosDe70Chars = "sites.com.sites.com.sites.com.sites.com.sites.com.sites.com.sites";
		String urlCom70Chars = "sites.com.sites.com.sites.com.sites.com.sites.com.sites.com.sites.com.";
		String urlComMaisDe70Chars = "sites.com.sites.com.sites.com.sites.com.sites.com.sites.com.sites.com.asdas.";
		
		
		VideoInputDTO dto1 = new VideoInputDTO("Titulo do Video 1", "descrição do video 1", urlComMenosDe70Chars, 1L);
		VideoInputDTO dto2 = new VideoInputDTO("Titulo do Video 2", "descrição do video 2", urlCom70Chars, 1L);
		VideoInputDTO dto3 = new VideoInputDTO("Titulo do Video 3", "descrição do video 3", urlComMaisDe70Chars, 1L);
				
		assertTrue(service.errosDoDTO(dto1).isEmpty());
		assertTrue(service.errosDoDTO(dto2).isEmpty());
		assertFalse(service.errosDoDTO(dto3).isEmpty());
	}
	
	@Test
	void naoDeveriaCadastrarURLSemFormatoDeUrl() {
		String urlSemFormato = "ora, ora!...";
		String urlComFormato = "www.sites.com.br";
		
		VideoInputDTO dto1 = new VideoInputDTO("Titulo do Video 1", "descrição do video 1", urlSemFormato, 1L);
		VideoInputDTO dto2 = new VideoInputDTO("Titulo do Video 1", "descrição do video 2", urlComFormato, 1L);
	
		assertFalse(service.errosDoDTO(dto1).isEmpty());
		assertTrue(service.errosDoDTO(dto2).isEmpty());
	}
	
	@Test
	void naoDeveriaAceitarIdDeVideoInexistente() {
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
		
		assertTrue(service.isVideoIdValid(idValido));
		assertFalse(service.isVideoIdValid(idInvalido));
		assertThrows(IllegalArgumentException.class, () -> service.isVideoIdValid(null));
	}
	
	@Test
	void naoDeveriaAceitarIdDeCategoriaInexistente() {
		Mockito.when(categoriaRepository.existsById(1L)).thenReturn(true);
		Mockito.when(categoriaRepository.existsById(null)).thenThrow(IllegalArgumentException.class);
		
		long randomInt = Math.round(Math.random()*100);
		long idValido = 1L;
		long idInvalido = 0L;
		
		if (randomInt == 1L) {
			idInvalido = randomInt + Math.round(Math.random()*100);
		} else {
			idInvalido = randomInt;
		}
		
		assertTrue(service.isCategoriaIdValid(idValido));
		assertFalse(service.isCategoriaIdValid(idInvalido));
		assertThrows(IllegalArgumentException.class, () -> service.isCategoriaIdValid(null));
	}

}
