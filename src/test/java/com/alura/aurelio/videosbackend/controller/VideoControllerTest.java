package com.alura.aurelio.videosbackend.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.alura.aurelio.videosbackend.service.VideoService;

class VideoControllerTest {
	
	@Mock
	private VideoService service;
	
	@Mock
	private VideoController controller;
	
	@BeforeEach
	void beforeEarch() {
		MockitoAnnotations.openMocks(this);
		this.controller = new VideoController(service);
	}

	@Test
	void nãoDeveriaAceitarIdVideoNuloParaMetodosPutEDelete() {
		assertFalse(controller.idIsNull(1L));
		assertTrue(controller.idIsNull(null));
	}

}
