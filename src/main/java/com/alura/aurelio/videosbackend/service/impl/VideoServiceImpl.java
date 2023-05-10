package com.alura.aurelio.videosbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.aurelio.videosbackend.domain.Video;
import com.alura.aurelio.videosbackend.repository.VideoRepository;
import com.alura.aurelio.videosbackend.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	
	@Autowired VideoRepository repository;

	@Override
	public List<Video> obterTodos() {
		return repository.findAll();
	}

	@Override
	public Video obter(Long id) {
		// TODO implementar obter(video)
		return null;
	}

	@Override
	public void criar(Video video) {
		// TODO implementar criar(video)
		
	}

	@Override
	public void atualizar(Video video) {
		// TODO implementar atualizar(video)
		
	}

	@Override
	public void remover(Video video) {
		// TODO implementar remover(video)
		
	}

}
