package com.alura.aurelio.videosbackend.service;

import java.util.List;
import java.util.Optional;

import com.alura.aurelio.videosbackend.domain.Video;
import com.alura.aurelio.videosbackend.domain.VideoInputDTO;
import com.alura.aurelio.videosbackend.infra.CustomException;

public interface VideoService {
	
	List<Video> obterTodos();
	Optional<Video> obter(Long id) throws CustomException;
	void criar(VideoInputDTO video);
	void atualizar(Video video, Long id) throws CustomException;
	void remover(Long id) throws CustomException;
	
}
