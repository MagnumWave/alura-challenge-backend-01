package com.alura.aurelio.videosbackend.service;

import java.util.List;
import java.util.Optional;

import com.alura.aurelio.videosbackend.domain.Video;

public interface VideoService {
	
	List<Video> obterTodos();
	Optional<Video> obter(Long id);
	void criar(Video video);
	void atualizar(Video video, Long id);
	void remover(Long id);
	
}
