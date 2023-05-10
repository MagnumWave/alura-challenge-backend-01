package com.alura.aurelio.videosbackend.service;

import java.util.List;

import com.alura.aurelio.videosbackend.domain.Video;

public interface VideoService {
	
	List<Video> obterTodos();
	Video obter(Long id);
	void criar(Video video);
	void atualizar(Video video);
	void remover(Video video);
	
}
