package com.alura.aurelio.videosbackend.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.alura.aurelio.videosbackend.repository.CategoriaRepository;
import com.alura.aurelio.videosbackend.service.CategoriaService;
import com.alura.aurelio.videosbackend.service.VideoService;

class CategoriaControllerTest {
	
	@Mock
	private CategoriaRepository repository;
	
	@Mock
	private CategoriaService service;
	
	@Mock
	private VideoService videoService;
	
	@Mock
	private CategoriaController controller;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.controller = new CategoriaController(service, videoService);
	}

	@Test
	void nãoDeveriaAceitarIdNuloParaMetodosPutEDelete() {
		assertTrue(controller.idIsNull(null));
	}

}
