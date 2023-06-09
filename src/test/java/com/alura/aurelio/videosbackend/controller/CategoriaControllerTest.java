package com.alura.aurelio.videosbackend.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.alura.aurelio.videosbackend.service.CategoriaService;
import com.alura.aurelio.videosbackend.service.VideoService;

class CategoriaControllerTest {
	
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
		assertFalse(controller.idIsNull(1L));
		assertTrue(controller.idIsNull(null));
	}

}
