package com.alura.aurelio.videosbackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.aurelio.videosbackend.domain.Video;
import com.alura.aurelio.videosbackend.domain.VideoInputDTO;
import com.alura.aurelio.videosbackend.service.VideoService;

@RestController
@RequestMapping("/videos")
public class VideoController {
	
	@Autowired VideoService service;

	@GetMapping
	public List<Video> getAll() {
		return service.obterTodos();
		
	}
	
	@GetMapping("/{id}")
	public Optional<Video> getById(@PathVariable Long id) {
		return service.obter(id);
	}
	
	@PostMapping
	public void post(@Validated(VideoInputDTO.class) @RequestBody VideoInputDTO videoDTO) throws Exception {
		service.criar(videoDTO.toVideo());
	}
	
	@PutMapping("/{id}")
	public void put(@Validated(VideoInputDTO.class) @RequestBody VideoInputDTO videoDTO, @PathVariable Long id) throws Exception {
		service.atualizar(videoDTO.toVideo(), id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.remover(id);
	}
	//TODO: implementar exceção personalizada com a mensagem do erro. ExceptionHandler!
}
